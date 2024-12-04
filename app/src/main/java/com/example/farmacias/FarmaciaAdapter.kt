package com.example.farmacias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FarmaciaAdapter(private val listaFarmacias: List<Farmacia>, private val onItemClick: (Farmacia) -> Unit) :
    RecyclerView.Adapter<FarmaciaAdapter.FarmaciaViewHolder>() {

    inner class FarmaciaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        private val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)

        fun enlazar(farmacia: Farmacia) {
            tvNombre.text = farmacia.nombre
            tvTelefono.text = farmacia.telefono
            itemView.setOnClickListener { onItemClick(farmacia) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmaciaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_farmacia, parent, false)
        return FarmaciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmaciaViewHolder, position: Int) {
        holder.enlazar(listaFarmacias[position])
    }

    override fun getItemCount() = listaFarmacias.size
}
