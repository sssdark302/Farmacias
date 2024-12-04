package com.example.farmacias

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap

class MapaFarmaciaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapaFarmaciaBinding
    private lateinit var map: GoogleMap
    private lateinit var farmacia: Farmacia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaFarmaciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        farmacia = intent.getParcelableExtra("farmacia")!!
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync {
            map = it
            val ubicacionFarmacia = LatLng(farmacia.latitud, farmacia.longitud)
            map.addMarker(MarkerOptions().position(ubicacionFarmacia).title(farmacia.nombre))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionFarmacia, 15f))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }
}