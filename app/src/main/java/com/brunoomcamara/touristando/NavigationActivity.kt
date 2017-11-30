package com.brunoomcamara.touristando

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.brunoomcamara.touristando.Model.ConnReceiver
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.brunoomcamara.touristando.Service.PontoTuristicoService
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.content_navigation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var estados: Spinner? = null
    var cidades: Spinner? = null
    var categoria: Spinner? = null
    var cids: ArrayList<String>? = null
    var br = ConnReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        //testa se a internet esta ativa
        registerReceiver(br, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        // cria a lista(Spnner) no layout
        estados = findViewById(R.id.spinEstados) as Spinner
        val adapter = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item)//adapta a lista
        estados?.adapter = adapter//adciona a lista a view

        // cria a lista(Spnner) no layout
        categoria = findViewById(R.id.spinCategoria) as Spinner
        val adapter3 = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item)//adapta a lista
        categoria!!.adapter = adapter3//adciona a lista a view

        estados!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                pupularSpnnerCidade(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {//cria automatico
            }
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            sharePage()

        } else if (id == R.id.out) {
            out()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun sharePage() {
        var intentShare: Intent = Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain")
        intentShare.putExtra(Intent.EXTRA_TEXT, "https://faustikle.wixsite.com/touristando")
        startActivity(intentShare)
    }

    private fun out() {
        var prefs = getSharedPreferences("arquivo_de_preferencias", 0)// cria o arquivo de preferencia
        var editor = prefs.edit()
        editor.putBoolean("estaLogado", false)// seta como verdadeiro
        editor.commit()//salva a preferencia no arquivo de preferencia
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun buscar(v: View) {

        var cid = cidades!!.selectedItem.toString()
        val service = PontoTuristicoService()
        val call = service.porCidade(cid)//busca no servidor o ponto pela cidade
        call.enqueue(object : Callback<List<PontoTuristico>> {

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
                        val intent = Intent(this@NavigationActivity, ListagemPontosActivity::class.java)
                        intent.putExtra("cidade", cid) //armazena o nome da cidade para ser usado na proxima tela
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<PontoTuristico>>, t: Throwable) {
                Toast.makeText(this@NavigationActivity, "Nenhum ponto turístico encontrado", Toast.LENGTH_SHORT).show()
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
