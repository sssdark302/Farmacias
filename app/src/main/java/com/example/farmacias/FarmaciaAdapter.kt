package com.example.farmacias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FarmaciaAdapter(private val listaFarmacias: List<Farmacia>, private val onItemClick: (Farmacia) -> Unit) :
    RecyclerView.Adapter<FarmaciaAdapter.FarmaciaViewHolder>() {

    inner class FarmaciaViewHolder(val binding: ItemFarmaciaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun enlazar(farmacia: Farmacia) {
            binding.tvNombre.text = farmacia.nombre
            binding.tvTelefono.text = farmacia.telefono
            binding.root.setOnClickListener { onItemClick(farmacia) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmaciaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFarmaciaBinding.inflate(inflater, parent, false)
        return FarmaciaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FarmaciaViewHolder, position: Int) {
        holder.enlazar(listaFarmacias[position])
    }

    override fun getItemCount() = listaFarmacias.size
}
