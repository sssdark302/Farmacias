package com.example.farmacias

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ListaFarmaciasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaFarmaciasBinding
    private lateinit var database: DatabaseReference
    private val listaFarmacias = mutableListOf<Farmacia>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFarmaciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().reference.child("farmacias")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val adapter = FarmaciaAdapter(listaFarmacias) { farmacia ->
            val intent = Intent(this, MapaFarmaciaActivity::class.java)
            intent.putExtra("farmacia", farmacia)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerUbicacion()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaFarmacias.clear()
                for (data in snapshot.children) {
                    data.getValue(Farmacia::class.java)?.let { listaFarmacias.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ListaFarmaciasActivity, "Error cargando datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                Toast.makeText(
                    this,
                    "Tu ubicación actual: Lat: ${it.latitude}, Lng: ${it.longitude}",
                    Toast.LENGTH_LONG
                ).show()
            } ?: Toast.makeText(this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
        }
    }
}