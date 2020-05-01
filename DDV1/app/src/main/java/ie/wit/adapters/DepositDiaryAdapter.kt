package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.R
import ie.wit.models.DepositDiaryModel
import kotlinx.android.synthetic.main.card_deposit_diary.view.*

class DepositDiaryAdapter constructor(private var depositDiaries: List<DepositDiaryModel>)
    : RecyclerView.Adapter<DepositDiaryAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_deposit_diary,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = depositDiaries[holder.adapterPosition]
        holder.bind(donation)
    }

    override fun getItemCount(): Int = depositDiaries.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(depositDiary: DepositDiaryModel) {
            itemView.date.text = depositDiary.date
            itemView.typepicker.text = depositDiary.type.toString()
            itemView.motionpicker.text = depositDiary.motion.toString()
            itemView.durationpicker.text = depositDiary.duration.toString()
            itemView.painpicker.text = depositDiary.pain.toString()
            itemView.typeofday.text = depositDiary.day
        }
    }
}