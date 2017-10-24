package com.brunoomcamara.touristando.Model

import com.orm.SugarRecord

class PontoTuristico: SugarRecord() {

    val _id: String = ""
    var nome: String = ""
    var cidade: String = ""
    val imagem: String = ""
    var endereco: String = ""
    val lat: String = ""
    val log: String = ""
    val nota: Int? = null

    fun PontoTuristico(){}

    fun PontoTuristico(nome: String, endereco: String){
        this.nome = nome
        this.endereco = endereco
    }


}

