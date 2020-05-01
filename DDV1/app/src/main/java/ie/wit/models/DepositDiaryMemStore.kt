package ie.wit.models

import android.util.Log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class DepositDiaryMemStore : DepositDiaryStore {

        val deposits = ArrayList<DepositDiaryModel>()

        override fun findAll(): List<DepositDiaryModel> {
            return deposits
        }

        override fun findById(id:Long) : DepositDiaryModel? {
            val foundDepositDiary: DepositDiaryModel? = deposits.find { it.id == id }
            return foundDepositDiary
        }

        override fun create(depositDiary: DepositDiaryModel) {
            depositDiary.id = getId()
            deposits.add(depositDiary)
            logAll()
        }

        fun logAll() {
            Log.v("Deposit","** Deposits List **")
            deposits.forEach { Log.v("Deposit","${it}") }
        }
    }
