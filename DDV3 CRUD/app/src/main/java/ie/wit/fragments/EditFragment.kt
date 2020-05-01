package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import ie.wit.R
import ie.wit.main.DepositDiaryApp
import ie.wit.models.DepositDiaryModel
import ie.wit.utils.createLoader
import ie.wit.utils.hideLoader
import ie.wit.utils.showLoader
import kotlinx.android.synthetic.main.fragment_edit.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class EditFragment : Fragment(), AnkoLogger {

    lateinit var app: DepositDiaryApp
    lateinit var loader : AlertDialog
    lateinit var root: View
    var editDepositDiary: DepositDiaryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DepositDiaryApp

        arguments?.let {
            editDepositDiary = it.getParcelable("editdeposit")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_edit, container, false)
        activity?.title = getString(R.string.action_edit)
        loader = createLoader(activity!!)

        root.editPain.setText(editDepositDiary!!.pain.toString())
        root.editMotions.setText(editDepositDiary!!.motion.toString())
        root.editNote.setText(editDepositDiary!!.note)



        root.editUpdateButton.setOnClickListener {
            showLoader(loader, "Updating Deposit on Server...")
            updateDepositDiaryData()
            updateDepositDiary(editDepositDiary!!.uid, editDepositDiary!!)
            updateUserDepositDiary(app.auth.currentUser!!.uid,
                               editDepositDiary!!.uid, editDepositDiary!!)
        }

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(depositDiary: DepositDiaryModel) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("editdeposit",depositDiary)
                }
            }
    }

    fun updateDepositDiaryData() {
        editDepositDiary!!.motion = root.editMotions.text.toString().toInt()
        editDepositDiary!!.pain = root.editPain.text.toString().toInt()
        editDepositDiary!!.note = root.editNote.text.toString()
    }

    fun updateUserDepositDiary(userId: String, uid: String?, depositDiary: DepositDiaryModel) {
        app.database.child("user-deposits").child(userId).child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.ref.setValue(depositDiary)
                        activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.homeFrame, EntriesFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                        hideLoader(loader)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        info("Firebase Deposit error : ${error.message}")
                    }
                })
    }

    fun updateDepositDiary(uid: String?, depositDiary: DepositDiaryModel) {
        app.database.child("deposits").child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.ref.setValue(depositDiary)
                        hideLoader(loader)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        info("Firebase Deposit error : ${error.message}")
                    }
                })
    }
}
