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
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_deposit.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.HashMap


class DepositFragment : Fragment(), AnkoLogger {

    lateinit var app: DepositDiaryApp

    lateinit var loader : AlertDialog
    lateinit var eventListener : ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DepositDiaryApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_deposit, container, false)
        loader = createLoader(activity!!)
        activity?.title = getString(R.string.action_deposit)

        root.typePicker.minValue = 1
        root.typePicker.maxValue = 10

        root.motionPicker.minValue = 1
        root.motionPicker.maxValue = 10

        root.durationPicker.minValue = 1
        root.durationPicker.maxValue = 10

        root.painPicker.minValue = 1
        root.painPicker.maxValue = 10


        setButtonListener(root)
        return root;
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DepositFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun setButtonListener( layout: View) {
        layout.depositButton.setOnClickListener {
            val date = layout.Date.text.toString()
            val type = layout.typePicker.value
            val motion = layout.motionPicker.value
            val duration = layout.durationPicker.value
            val pain = layout.painPicker.value
            val day = if(layout.typeOfDay.checkedRadioButtonId == R.id.Bad) "Bad Day" else "Good Day"
                writeNewDepositDiary(DepositDiaryModel(date = date, day = day, type = type,
                    motion = motion, duration = duration, pain = pain,
                                               email = app.auth.currentUser?.email))
            }
        }


    override fun onResume() {
        super.onResume()
        getTotalDeposited(app.auth.currentUser?.uid)
    }

    override fun onPause() {
        super.onPause()
        if(app.auth.uid != null)
            app.database.child("user-deposits")
                    .child(app.auth.currentUser!!.uid)
                    .removeEventListener(eventListener)
    }

    fun writeNewDepositDiary(depositDiary: DepositDiaryModel) {
        // Create new donation at /donations & /donations/$uid
        showLoader(loader, "Adding Deposit to Firebase")
        info("Firebase DB Reference : $app.database")
        val uid = app.auth.currentUser!!.uid
        val key = app.database.child("deposits").push().key
        if (key == null) {
            info("Firebase Error : Key Empty")
            return
        }
        depositDiary.uid = key
        val donationValues = depositDiary.toMap()

        val childUpdates = HashMap<String, Any>()
        childUpdates["/deposits/$key"] = donationValues
        childUpdates["/user-deposits/$uid/$key"] = donationValues

        app.database.updateChildren(childUpdates)
        hideLoader(loader)
    }

    fun getTotalDeposited(userId: String?) {
        eventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                info("Firebase Deposit error : ${error.message}")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val children = snapshot.children
                children.forEach {

                }

            }
        }

        app.database.child("user-deposits").child(userId!!)
            .addValueEventListener(eventListener)
    }
}
