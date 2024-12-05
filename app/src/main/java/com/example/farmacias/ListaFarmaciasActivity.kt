package com.example.farmacias

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class ListaFarmaciasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val listaFarmacias = mutableListOf<Farmacia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_farmacias)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = FarmaciaAdapter(listaFarmacias) { farmacia ->
            val intent = Intent(this, DetallesFarmaciaActivity::class.java)
            intent.putExtra("nombre", farmacia.nombre)
            intent.putExtra("telefono", farmacia.telefono)
            intent.putExtra("latitud", farmacia.latitud)
            intent.putExtra("longitud", farmacia.longitud)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        try {
            // Lee el archivo JSON y carga las farmacias
            val farmacias = leerFarmaciasDesdeJSON(R.raw.farmacias_equipamiento)
            listaFarmacias.addAll(farmacias)
            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al leer las farmacias: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun leerFarmaciasDesdeJSON(resourceId: Int): List<Farmacia> {
        val inputStream = resources.openRawResource(resourceId)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.use { it.readText() }
        val listaFarmacias = mutableListOf<Farmacia>()

        val jsonObject = JSONObject(jsonString)
        val features = jsonObject.getJSONArray("features")

        for (i in 0 until features.length()) {
            val feature = features.getJSONObject(i)
            val geometry = feature.getJSONObject("geometry")
            val coordinates = geometry.getJSONArray("coordinates")
            val properties = feature.getJSONObject("properties")

            val farmacia = Farmacia(
                nombre = properties.getString("title"),
                telefono = properties.optString("description").split("Teléfono: ").getOrNull(1)?.split(" ")?.get(0) ?: "No disponible",
                latitud = coordinates.getDouble(1),
                longitud = coordinates.getDouble(0)
            )
            listaFarmacias.add(farmacia)
        }

        return listaFarmacias
    }
}
