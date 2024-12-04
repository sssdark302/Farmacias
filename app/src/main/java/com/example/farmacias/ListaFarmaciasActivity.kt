package com.example.farmacias

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ListaFarmaciasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private val listaFarmacias = mutableListOf<Farmacia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_farmacias)

        // Inicializa RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = FarmaciaAdapter(listaFarmacias) { farmacia ->
            val intent = Intent(this, MapaFarmaciaActivity::class.java)
            intent.putExtra("nombre", farmacia.nombre)
            intent.putExtra("telefono", farmacia.telefono)
            intent.putExtra("latitud", farmacia.latitud)
            intent.putExtra("longitud", farmacia.longitud)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // Inicializa Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("farmacias")

        // Obtiene los datos de Firebase
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
}
