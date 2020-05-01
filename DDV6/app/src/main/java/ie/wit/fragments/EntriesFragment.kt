package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

import ie.wit.R
import ie.wit.adapters.DepositDiaryAdapter
import ie.wit.adapters.DepositDiaryListener
import ie.wit.main.DepositDiaryApp
import ie.wit.models.DepositDiaryModel
import ie.wit.utils.*
import kotlinx.android.synthetic.main.fragment_entries.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

open class EntriesFragment : Fragment(), AnkoLogger,
    DepositDiaryListener {

    lateinit var app: DepositDiaryApp
    lateinit var loader : AlertDialog
    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DepositDiaryApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_entries, container, false)
        activity?.title = getString(R.string.action_entries)

        root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        setSwipeRefresh()

        val swipeDeleteHandler = object : SwipeToDeleteCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = root.recyclerView.adapter as DepositDiaryAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                deleteDepositDiary((viewHolder.itemView.tag as DepositDiaryModel).uid)
                deleteUserDepositDiary(app.currentUser!!.uid,
                                  (viewHolder.itemView.tag as DepositDiaryModel).uid)
            }
        }
        val itemTouchDeleteHelper = ItemTouchHelper(swipeDeleteHandler)
        itemTouchDeleteHelper.attachToRecyclerView(root.recyclerView)

        val swipeEditHandler = object : SwipeToEditCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onDepositDiaryClick(viewHolder.itemView.tag as DepositDiaryModel)
            }
        }
        val itemTouchEditHelper = ItemTouchHelper(swipeEditHandler)
        itemTouchEditHelper.attachToRecyclerView(root.recyclerView)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EntriesFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    open fun setSwipeRefresh() {
        root.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                root.swiperefresh.isRefreshing = true
                getAllDepositDiarys(app.currentUser!!.uid)
            }
        })
    }

    fun checkSwipeRefresh() {
        if (root.swiperefresh.isRefreshing) root.swiperefresh.isRefreshing = false
    }

    fun deleteUserDepositDiary(userId: String, uid: String?) {
        app.database.child("user-deposits").child(userId).child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.ref.removeValue()
                    }
                    override fun onCancelled(error: DatabaseError) {
                        info("Firebase Deposits error : ${error.message}")
                    }
                })
    }

    fun deleteDepositDiary(uid: String?) {
        app.database.child("deposits").child(uid!!)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.ref.removeValue()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        info("Firebase Deposits error : ${error.message}")
                    }
                })
    }

    override fun onDepositDiaryClick(depositDiary: DepositDiaryModel) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrame, EditFragment.newInstance(depositDiary))
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        getAllDepositDiarys(app.currentUser!!.uid)
    }

    fun getAllDepositDiarys(userId: String?) {
        loader = createLoader(activity!!)
        showLoader(loader, "Downloading Deposits from Firebase")
        val donationsList = ArrayList<DepositDiaryModel>()
        app.database.child("user-deposits").child(userId!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    info("Firebase Deposit error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    hideLoader(loader)
                    val children = snapshot.children
                    children.forEach {
                        val donation = it.
                            getValue<DepositDiaryModel>(DepositDiaryModel::class.java)

                        donationsList.add(donation!!)
                        root.recyclerView.adapter =
                            DepositDiaryAdapter(donationsList, this@EntriesFragment,false)
                        root.recyclerView.adapter?.notifyDataSetChanged()
                        checkSwipeRefresh()

                        app.database.child("user-deposits").child(userId)
                            .removeEventListener(this)
                    }
                }
            })
    }
}
