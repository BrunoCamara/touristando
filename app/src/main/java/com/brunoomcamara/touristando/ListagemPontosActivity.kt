package com.brunoomcamara.touristando
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.brunoomcamara.touristando.Adapter.PontoTuristicoAdapter
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_listagem_pontos.*

class ListagemPontosActivity : AppCompatActivity() {

    var cidade = ""

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

        cidade = intent.extras.getString("cidade")
        val pontos = SugarRecord.find(PontoTuristico::class.java, "cidade = ?", cidade)
        pontoTuristicoAdapter.addPontos(pontos)


        //Menu inferior com icone ddo mapa e lista
       bottonNavView.setOnNavigationItemSelectedListener {item ->

           if (item.itemId == R.id.itemListPontos) {
               Toast.makeText(this, "Lista de lugares encontrados", Toast.LENGTH_SHORT).show()

            } else if (item.itemId == R.id.itemMap){
               itemMapa()
           }
            true
        }
    }

    fun itemMapa(){

        var intent = Intent(this@ListagemPontosActivity, MapsActivity::class.java)
        intent.putExtra("cid", cidade)
        startActivity(intent)
    }
}
