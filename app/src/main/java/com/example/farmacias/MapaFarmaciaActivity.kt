package com.example.farmacias

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFarmaciaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var farmacia: Farmacia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_farmacia)

        val nombre = intent.getStringExtra("nombre") ?: "Nombre no disponible"
        val telefono = intent.getStringExtra("telefono") ?: "Teléfono no disponible"
        val latitud = intent.getDoubleExtra("latitud", 0.0)
        val longitud = intent.getDoubleExtra("longitud", 0.0)

        val ubicacionFarmacia = LatLng(latitud, longitud)

        // Configura el mapa con la ubicación
        val mapView: MapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            googleMap.addMarker(MarkerOptions().position(ubicacionFarmacia).title(nombre))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionFarmacia, 15f))
        }
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Muestra la ubicación de la farmacia en el mapa
        val ubicacion = LatLng(farmacia.latitud, farmacia.longitud)
        googleMap.addMarker(MarkerOptions().position(ubicacion).title(farmacia.nombre))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
    }

    // Métodos del ciclo de vida del MapView
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
