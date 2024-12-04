package com.example.farmacias

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ListaFarmaciasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaFarmaciasBinding
    private lateinit var firebaseHandler: FirebaseHandler
    private val listaFarmacias = mutableListOf<Farmacia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFarmaciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseHandler = FirebaseHandler()
        val adapter = FarmaciaAdapter(listaFarmacias) { farmacia ->
            val intent = Intent(this, MapaFarmaciaActivity::class.java)
            intent.putExtra("farmacia", farmacia)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        firebaseHandler.obtenerFarmacias(
            onResult = { farmacias ->
                listaFarmacias.clear()
                listaFarmacias.addAll(farmacias)
                adapter.notifyDataSetChanged()
            },
            onError = {
                Toast.makeText(this, "Error al cargar las farmacias", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
