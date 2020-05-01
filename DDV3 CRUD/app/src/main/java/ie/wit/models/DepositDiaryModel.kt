package ie.wit.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class DepositDiaryModel(
    var uid: String? = "",
    var date: String = "",
    var note: String = "Add note",
    var day: String = "N/A",
    var type: Int = 0,
    var motion: Int = 0,
    var duration: Int = 0,
    var pain: Int = 0,
    var email: String? = "joe@bloggs.com")
                        : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "pain" to pain,
            "duration" to duration,
            "motion" to motion,
            "type" to type,
            "day" to day,
            "date" to date,
            "note" to note,
            "email" to email
        )
    }
}


