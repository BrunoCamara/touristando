package com.brunoomcamara.touristando.Service

import com.brunoomcamara.touristando.Model.PontoTuristico
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPontoTuristicoService {
    @GET("/pontos")
    fun listarTodos(): Call<List<PontoTuristico>>

    @GET("/pontos")
    fun buscarPorCidade(@Query("cidade")cidade: String): Call<List<PontoTuristico>>
}
