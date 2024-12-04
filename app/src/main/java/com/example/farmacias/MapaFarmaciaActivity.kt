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

        // Inicializa el MapView y configura el callback
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Obtiene los datos de la farmacia desde el intent
        farmacia = intent.getParcelableExtra("farmacia")
            ?: throw IllegalArgumentException("No se pudo cargar la farmacia seleccionada")
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
