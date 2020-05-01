package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import ie.wit.R
import ie.wit.adapters.DepositDiaryAdapter
import ie.wit.main.DepositDiaryApp
import kotlinx.android.synthetic.main.fragment_entries.view.*

class EntriesFragment : Fragment() {

    lateinit var app: DepositDiaryApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DepositDiaryApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_entries, container, false)
        activity?.title = getString(R.string.action_entries)

        root.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        root.recyclerView.adapter = DepositDiaryAdapter(app.depositsStore.findAll())

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EntriesFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}
