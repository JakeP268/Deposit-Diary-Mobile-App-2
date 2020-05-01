package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DepositDiaryModel(var id: Long = 0,
                             val day: String = "N/A",
                             val date: String = "",
                             val type: Int = 0,
                             val motion: Int = 0,
                             val duration: Int = 0,
                             val pain: Int = 0) : Parcelable
