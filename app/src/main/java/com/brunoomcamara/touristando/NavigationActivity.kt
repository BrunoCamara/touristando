package com.brunoomcamara.touristando

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.brunoomcamara.touristando.Adapter.EstadoAdapter
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.brunoomcamara.touristando.Service.PontoTuristicoService
import java.util.ArrayList

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        val recyclerEstados = findViewById(R.id.lista_estados) as RecyclerView
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val estadoAdapter = EstadoAdapter(ArrayList(), {
            Toast.makeText(this, it.nome, Toast.LENGTH_SHORT).show()
            val intent = Intent(this@NavigationActivity, CidadeActivity::class.java)
            intent.putExtra("estadoId", it.id)
            startActivity(intent)
        })

        recyclerEstados.layoutManager = layoutManager
        recyclerEstados.adapter = estadoAdapter
        recyclerEstados.setHasFixedSize(true)

        estadoAdapter.inicializarEstados()
    }

    fun baixarCidade(v: View) {
        val service = PontoTuristicoService()
        val pontos = service.porCidade("João Pessoa")
//        if (pontos != null) {
//            for(ponto: PontoTuristico in pontos.iterator()) {
//                println(ponto.nome)
//            }
//        }
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

            sharePade()

        } else if (id == R.id.out) {
            out()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun sharePade() {
        var intentShare:Intent = Intent(Intent.ACTION_SEND);
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
}
