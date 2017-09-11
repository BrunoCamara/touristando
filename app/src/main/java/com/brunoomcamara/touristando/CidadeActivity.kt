package com.brunoomcamara.touristando

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.brunoomcamara.touristando.Adapter.CidadeAdapter
import com.brunoomcamara.touristando.Model.Cidade
import kotlin.collections.ArrayList

class CidadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cidade)

        val recyclerCidades = findViewById(R.id.lista_cidades) as RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val cidadeAdapter = CidadeAdapter(ArrayList(), {
            Toast.makeText(this, it.titulo, Toast.LENGTH_SHORT).show()
        })

        recyclerCidades.layoutManager = layoutManager
        recyclerCidades.adapter = cidadeAdapter
        recyclerCidades.setHasFixedSize(true)

        val estadoId = intent.extras.getInt("estadoId")
        cidadeAdapter.addCidades(obterCidadesPeloId(estadoId))
    }

    fun obterCidadesPeloId(id: Int): MutableList<Cidade> {
        when (id) {
            1 -> return mutableListOf(Cidade("Rio Branco"))
            2 -> return mutableListOf(Cidade("Maceió"))
            3 -> return mutableListOf(Cidade("Macapá"))
            4 -> return mutableListOf(Cidade("Manaus"))
            5 -> return mutableListOf(Cidade("Salvador"))
            6 -> return mutableListOf(Cidade("Fortaleza"))
            7 -> return mutableListOf(Cidade("Brasília"))
            8 -> return mutableListOf(Cidade("Vitória"))
            9 -> return mutableListOf(Cidade("Goiânia"))
            10 -> return mutableListOf(Cidade("São Luís"))
            11 -> return mutableListOf(Cidade("Cuiabá"))
            12 -> return mutableListOf(Cidade("Campo Grande"))
            13 -> return mutableListOf(Cidade("Belo Horizonte"))
            14 -> return mutableListOf(Cidade("Belém"))
            15 -> return mutableListOf(
                    Cidade("João Pessoa"),
                    Cidade("Campina Grande"),
                    Cidade("Santa Rita"))
            16 -> return mutableListOf(Cidade("Curitiba"))
            17 -> return mutableListOf(Cidade("Recife"))
            18 -> return mutableListOf(Cidade("Teresina"))
            19 -> return mutableListOf(Cidade("Rio de Janeiro"))
            20 -> return mutableListOf(Cidade("Natal"))
            21 -> return mutableListOf(Cidade("Porto Alegre"))
            22 -> return mutableListOf(Cidade("Porto Velho"))
            23 -> return mutableListOf(Cidade("Boa Vista"))
            24 -> return mutableListOf(Cidade("Florianópolis"))
            25 -> return mutableListOf(Cidade("São Paulo"))
            26 -> return mutableListOf(Cidade("Aracaju"))
            27 -> return mutableListOf(Cidade("Palmas"))
            else -> return ArrayList()
        }
    }
}
