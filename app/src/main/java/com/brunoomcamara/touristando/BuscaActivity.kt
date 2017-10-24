package com.brunoomcamara.touristando

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.util.ArrayList


import android.R.layout.simple_spinner_item
import android.content.Intent
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.brunoomcamara.touristando.Service.PontoTuristicoService
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_busca.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuscaActivity : AppCompatActivity() {

    var estados: Spinner? = null
    var cidades: Spinner? = null
    var categoria: Spinner? = null
    var cids: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)





        // cria a lista(Spnner) no layout
        estados = findViewById(R.id.spinEstados) as Spinner
        val adapter = ArrayAdapter.createFromResource(this, R.array.estados, simple_spinner_item)//adapta a lista
        estados!!.adapter = adapter//adciona a lista a view

        // cria a lista(Spnner) no layout
        categoria = findViewById(R.id.spinCategoria) as Spinner
        val adapter3 = ArrayAdapter.createFromResource(this, R.array.categorias, simple_spinner_item)//adapta a lista
        categoria!!.adapter = adapter3//adciona a lista a view

        estados!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                pupularSpnnerCidade(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {//cria automatico
            }
        }

    }

    fun buscar(v: View) {
        val cid = cidades!!.selectedItem.toString()
        val service = PontoTuristicoService()
        val call = service.porCidade(cid)
        call.enqueue(object: Callback<List<PontoTuristico>> {

            override fun onResponse(call: Call<List<PontoTuristico>>, response: Response<List<PontoTuristico>>) {
                if (response.isSuccessful()) {
                    val pontos = response.body()
                    if (pontos != null) {
                        for (ponto: PontoTuristico in pontos.iterator()) {
                            val pontoBuscado = SugarRecord.find(PontoTuristico::class.java, "_id = ?", ponto._id)

                            if (pontoBuscado.isEmpty()) {
                                ponto.save()
                            }
                        }

                        val intent = Intent(this@BuscaActivity, ListagemPontosActivity::class.java)
                        intent.putExtra("cidade", cid)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<PontoTuristico>>, t: Throwable) {
                // Erro ao consumir recurso
            }
        })

    }

    fun pupularSpnnerCidade(id: Int) {

        cids = obterCidadesPelo(id + 1)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cids)

        cidades = findViewById(R.id.spinCidades) as Spinner
        cidades!!.adapter = adapter2

    }

    fun obterCidadesPelo(id: Int): ArrayList<String> {

        val cidade = ArrayList<String>()

        when (id) {
            1 -> cidade.add("Rio Branco")
            2 -> cidade.add("Maceió")
            3 -> cidade.add("Macapá")
            4 -> cidade.add("Manaus")
            5 -> cidade.add("Salvador")
            6 -> cidade.add("Fortaleza")
            7 -> cidade.add("Brasília")
            8 -> cidade.add("Vitória")
            9 -> cidade.add("Goiânia")
            10 -> cidade.add("São Luís")
            11 -> cidade.add("Cuiabá")
            12 -> cidade.add("Campo Grande")
            13 -> cidade.add("Belo Horizonte")
            14 -> cidade.add("Belém")
            15 -> {
                cidade.add("João Pessoa")
                cidade.add("Campina Grande")
                cidade.add("Santa Rita")
            }
            16 -> cidade.add("Curitiba")
            17 -> cidade.add("Recife")
            18 -> cidade.add("Teresina")
            19 -> cidade.add("Rio de Janeiro")
            20 -> cidade.add("Natal")
            21 -> cidade.add("Porto Alegre")
            22 -> cidade.add("Porto Velho")
            23 -> cidade.add("Boa Vista")
            24 -> cidade.add("Florianópolis")
            25 -> cidade.add("São Paulo")
            26 -> cidade.add("Aracaju")
            27 -> cidade.add("Palmas")
        }
        return cidade
    }
}


