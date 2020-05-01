package ie.wit.main

import android.app.Application
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import ie.wit.models.DepositDiaryMemStore
import ie.wit.models.DepositDiaryStore

class DepositDiaryApp : Application() {

    lateinit var depositsStore: DepositDiaryStore

    // [START declare_auth]
    lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate() {
        super.onCreate()
        depositsStore = DepositDiaryMemStore()
        Log.v("Deposit","Deposit Diary App started")
    }
}