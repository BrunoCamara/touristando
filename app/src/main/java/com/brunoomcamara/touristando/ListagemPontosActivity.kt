package com.brunoomcamara.touristando

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.brunoomcamara.touristando.Adapter.PontoTuristicoAdapter
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.brunoomcamara.touristando.Service.PontoTuristicoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val service = PontoTuristicoService()
        val call = service.porCidade("João Pessoa")
        call.enqueue(object: Callback<List<PontoTuristico>> {

            override fun onResponse(call: Call<List<PontoTuristico>>, response: Response<List<PontoTuristico>>) {
                if (response.isSuccessful()) {
                    println(response.body())
                    val pontos = response.body()

                    if (pontos != null) {
                        pontoTuristicoAdapter.addPontos(pontos)
                    }
                }
            }

            override fun onFailure(call: Call<List<PontoTuristico>>, t: Throwable) {
                // Erro ao consumir recurso
            }
        })
    }

}
