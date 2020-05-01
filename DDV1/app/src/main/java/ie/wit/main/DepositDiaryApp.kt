package ie.wit.main

import android.app.Application
import android.util.Log
import ie.wit.models.DepositDiaryMemStore
import ie.wit.models.DepositDiaryStore

class DepositDiaryApp : Application() {

    lateinit var depositsStore: DepositDiaryStore

    override fun onCreate() {
        super.onCreate()
        depositsStore = DepositDiaryMemStore()
        Log.v("Deposit","Deposit Diary App started")
    }
}