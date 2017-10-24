package com.brunoomcamara.touristando.Service

import com.brunoomcamara.touristando.Model.PontoTuristico
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PontoTuristicoService {
    private val service: IPontoTuristicoService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://138.197.98.219:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.service = retrofit.create(IPontoTuristicoService::class.java)
    }

    fun porCidade(cidade: String): Call<List<PontoTuristico>> {
        return this.service.buscarPorCidade(cidade)
    }

    fun todos(): List<PontoTuristico>? {
        val call = this.service.listarTodos()
        return call.execute().body()
    }
}
