package ro.araducanu.rentconnect.app

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

    }
}