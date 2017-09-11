package com.brunoomcamara.touristando.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brunoomcamara.touristando.Model.Estado
import com.brunoomcamara.touristando.R
import com.brunoomcamara.touristando.ViewHolder.EstadoHolder

class EstadoAdapter(private val estados: MutableList<Estado>, val onClickEstado: (Estado)->Unit) : RecyclerView.Adapter<EstadoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoHolder {
        return EstadoHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_line_text, parent, false))
    }

    override fun onBindViewHolder(holder: EstadoHolder, position: Int) {
        holder.titulo.text = estados.get(position).nome
        holder.titulo.setOnClickListener({onClickEstado(estados.get(position))})
    }

    override fun getItemCount(): Int = estados.size

    fun inicializarEstados() {
        estados.add(Estado(1, "Acre"))
        estados.add(Estado(2, "Alagoas"))
        estados.add(Estado(3, "Amapá"))
        estados.add(Estado(4, "Amazonas"))
        estados.add(Estado(5, "Bahia"))
        estados.add(Estado(6, "Ceará"))
        estados.add(Estado(7, "Distrito Federal"))
        estados.add(Estado(8, "Espírito Santo"))
        estados.add(Estado(9, "Goiás"))
        estados.add(Estado(10, "Maranhão"))
        estados.add(Estado(11, "Mato Grosso"))
        estados.add(Estado(12, "Mato Grosso do Sul"))
        estados.add(Estado(13, "Minas Gerais"))
        estados.add(Estado(14, "Pará"))
        estados.add(Estado(15, "Paraíba"))
        estados.add(Estado(16, "Paraná"))
        estados.add(Estado(17, "Pernambuco"))
        estados.add(Estado(18, "Piauí"))
        estados.add(Estado(19, "Rio de Janeiro"))
        estados.add(Estado(20, "Rio Grande do Norte"))
        estados.add(Estado(21, "Rio Grande do Sul"))
        estados.add(Estado(22, "Rondônia"))
        estados.add(Estado(23, "Roraima"))
        estados.add(Estado(24, "Santa Catarina"))
        estados.add(Estado(25, "São Paulo"))
        estados.add(Estado(26, "Sergipe"))
        estados.add(Estado(27, "Tocantins"))
        notifyItemInserted(getItemCount())
    }
}
