package com.brunoomcamara.touristando.Model

import com.orm.SugarRecord

class PontoTuristico: SugarRecord() {

    val id: String = ""
    var nome: String = ""
    val imagem: String = ""
    var endereco: String = ""
    val localizacao: Localizacao? = null
    val nota: Int? = null

    fun PontoTuristico(){}

    fun PontoTuristico(nome: String, endereco: String){
        this.nome = nome
        this.endereco = endereco
    }


}

