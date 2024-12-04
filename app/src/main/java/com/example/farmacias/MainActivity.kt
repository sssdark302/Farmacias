package com.example.farmacias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicia directamente la actividad de lista de farmacias
        val intent = Intent(this, ListaFarmaciasActivity::class.java)
        startActivity(intent)
        finish()
    }
}
