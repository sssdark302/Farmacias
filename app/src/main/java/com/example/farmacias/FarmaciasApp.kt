package com.example.farmacias

import android.app.Application
import com.google.firebase.FirebaseApp

class FarmaciasApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this) // Inicializa Firebase
    }
}