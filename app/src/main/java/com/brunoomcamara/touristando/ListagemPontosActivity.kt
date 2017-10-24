package com.brunoomcamara.touristando

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.brunoomcamara.touristando.Adapter.PontoTuristicoAdapter
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.orm.SugarRecord

class ListagemPontosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_pontos)

        val recyclerPontosTuristicos = findViewById(R.id.lista_cidades) as RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val pontoTuristicoAdapter = PontoTuristicoAdapter(ArrayList(), {
            Toast.makeText(this, it.nome, Toast.LENGTH_SHORT).show()
        })

        recyclerPontosTuristicos.layoutManager = layoutManager
        recyclerPontosTuristicos.adapter = pontoTuristicoAdapter
        recyclerPontosTuristicos.setHasFixedSize(true)
        var cidade = intent.extras.getString("cidade")
        val pontos = SugarRecord.find(PontoTuristico::class.java, "cidade = ?", cidade)
        pontoTuristicoAdapter.addPontos(pontos)
    }

}
