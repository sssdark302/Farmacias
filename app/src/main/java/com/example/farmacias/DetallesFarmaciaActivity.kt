package com.example.farmacias

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesFarmaciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_farmacia)

        val tvNombre: TextView = findViewById(R.id.tvNombre)
        val tvTelefono: TextView = findViewById(R.id.tvTelefono)
        val tvLatitud: TextView = findViewById(R.id.tvLatitud)
        val tvLongitud: TextView = findViewById(R.id.tvLongitud)
        val ivMapa: ImageView = findViewById(R.id.ivMapa)
        val btnVolver: Button = findViewById(R.id.btnVolver)

        // Recuperar datos del intent
        val nombre = intent.getStringExtra("nombre")
        val telefono = intent.getStringExtra("telefono")
        val latitud = intent.getDoubleExtra("latitud", 0.0)
        val longitud = intent.getDoubleExtra("longitud", 0.0)

        // Mostrar los datos
        tvNombre.text = "Nombre: $nombre"
        tvTelefono.text = "Teléfono: $telefono"
        tvLatitud.text = "Latitud: $latitud"
        tvLongitud.text = "Longitud: $longitud"

        // Mostrar un mapa genérico
        ivMapa.setImageResource(R.drawable.mapa_generico)

        // Funcionalidad del botón "Volver"
        btnVolver.setOnClickListener {
            finish() // Cierra esta actividad y regresa a la lista
        }
    }
}
