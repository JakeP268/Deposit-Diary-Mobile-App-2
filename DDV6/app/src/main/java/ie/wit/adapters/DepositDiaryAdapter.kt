package ie.wit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.R
import ie.wit.models.DepositDiaryModel
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.card_deposit_diary.view.*

interface DepositDiaryListener {
    fun onDepositDiaryClick(depositDiary: DepositDiaryModel)
}

class DepositDiaryAdapter constructor(var depositDiaries: ArrayList<DepositDiaryModel>,
                                  private val listener: DepositDiaryListener, reportall : Boolean)
    : RecyclerView.Adapter<DepositDiaryAdapter.MainHolder>() {

    val reportAll = reportall

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
        holder.bind(donation, listener, reportAll)
    }

    override fun getItemCount(): Int = depositDiaries.size

    fun removeAt(position: Int) {
        depositDiaries.removeAt(position)
        notifyItemRemoved(position)
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(depositDiary: DepositDiaryModel, listener: DepositDiaryListener, reportAll: Boolean) {
            itemView.tag = depositDiary
            itemView.date.text = depositDiary.date
            itemView.typepicker.text = depositDiary.type.toString()
            itemView.motionpicker.text = depositDiary.motion.toString()
            itemView.durationpicker.text = depositDiary.duration.toString()
            itemView.painpicker.text = depositDiary.pain.toString()
            itemView.typeofday.text = depositDiary.day

            if (!reportAll)
                itemView.setOnClickListener { listener.onDepositDiaryClick(depositDiary) }

            if (!depositDiary.profilepic.isEmpty()) {
                Picasso.get().load(depositDiary.profilepic.toUri())
                    //.resize(180, 180)
                    .transform(CropCircleTransformation())

            }
        }
    }
}