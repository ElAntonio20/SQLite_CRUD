package com.example.sqlite_crud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class RestauranteAdapter(private val listaRestaurantes: MutableList<Restaurante>) :
    RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    class RestauranteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tv_nombre)
        val tvDireccion: TextView = view.findViewById(R.id.tv_direccion)
        val tvTelefono: TextView = view.findViewById(R.id.tv_telefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestauranteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = listaRestaurantes[position]
        holder.tvNombre.text = restaurante.nombre
        holder.tvDireccion.text = restaurante.direccion
        holder.tvTelefono.text = restaurante.telefono
    }

    override fun getItemCount(): Int {
        return listaRestaurantes.size
    }
}
