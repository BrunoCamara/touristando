package com.brunoomcamara.touristando.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brunoomcamara.touristando.Model.Cidade
import com.brunoomcamara.touristando.R
import com.brunoomcamara.touristando.ViewHolder.CidadeHolder

class CidadeAdapter(private val cidades: MutableList<Cidade>, val onClickCidade: (Cidade)->Unit) : RecyclerView.Adapter<CidadeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CidadeHolder {
        return CidadeHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_line_text, parent, false))
    }

    override fun onBindViewHolder(holder: CidadeHolder, position: Int) {
        holder.titulo.text = cidades.get(position).titulo
        holder.titulo.setOnClickListener({onClickCidade(cidades.get(position))})
    }

    override fun getItemCount(): Int = cidades.size

    fun addCidades(cidades: MutableList<Cidade>) {
        for (cidade in cidades) {
            this.cidades.add(cidade)
        }

        notifyItemInserted(getItemCount())
        notifyItemRangeInserted(getItemCount(), cidades.size)
    }
}
