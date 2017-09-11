package com.brunoomcamara.touristando.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.brunoomcamara.touristando.R

class CidadeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titulo: TextView

    init {
        titulo = itemView.findViewById(R.id.titulo) as TextView
    }
}
