package ie.wit.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import ie.wit.R
import ie.wit.main.DepositDiaryApp
import ie.wit.models.DepositDiaryModel
import ie.wit.utils.createLoader
import kotlinx.android.synthetic.main.fragment_deposit.view.*


class DepositFragment : Fragment() {

    lateinit var app: DepositDiaryApp
    lateinit var loader : AlertDialog

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
                app.depositsStore.create(DepositDiaryModel(date = date, day = day, type = type,
                    motion = motion, duration = duration, pain = pain))
            }
        }


}
