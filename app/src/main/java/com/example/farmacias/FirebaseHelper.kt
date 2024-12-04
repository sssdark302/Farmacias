package com.example.farmacias

import com.google.firebase.database.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// Clase para manejar las interacciones con Firebase
class FirebaseHandler {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Referencia a la base de datos de farmacias
    private val farmaciaRef: DatabaseReference = database.reference.child("farmacias")

    // Obtener todas las farmacias de la base de datos
    fun obtenerFarmacias(onResult: (List<Farmacia>) -> Unit, onError: (Exception) -> Unit) {
        farmaciaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaFarmacias = mutableListOf<Farmacia>()
                for (data in snapshot.children) {
                    data.getValue(Farmacia::class.java)?.let { listaFarmacias.add(it) }
                }
                onResult(listaFarmacias)
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.toException())
            }
        })
    }

    // Obtener usuario autenticado
    fun obtenerUsuarioActual(): FirebaseUser? {
        return auth.currentUser
    }

    // Cerrar sesión
    fun cerrarSesion() {
        auth.signOut()
    }
}
