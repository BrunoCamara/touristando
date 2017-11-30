package com.brunoomcamara.touristando.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.brunoomcamara.touristando.R
import com.brunoomcamara.touristando.ViewHolder.PontoTuristicoHolder

class PontoTuristicoAdapter(private val pontosTuristicos: MutableList<PontoTuristico>, val onClickCidade: (PontoTuristico) -> Unit) : RecyclerView.Adapter<PontoTuristicoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PontoTuristicoHolder {
        return PontoTuristicoHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_line_text, parent, false))
    }

    override fun onBindViewHolder(holder: PontoTuristicoHolder, position: Int) {//
        holder.titulo.text = pontosTuristicos.get(position).nome

        holder.titulo.setOnClickListener({ onClickCidade(pontosTuristicos.get(position)) })//ação do clic no item do recycle
    }

    override fun getItemCount(): Int = pontosTuristicos.size//total de itens do recycleview

    //adciona os posntos ao recycleView
    fun addPontos(pontosTuristicos: List<PontoTuristico>) {
        for (pontoTuristico in pontosTuristicos) {
            this.pontosTuristicos.add(pontoTuristico)
        }

        notifyItemInserted(getItemCount())
        notifyItemRangeInserted(getItemCount(), pontosTuristicos.size)
    }
}
