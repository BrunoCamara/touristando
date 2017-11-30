package com.brunoomcamara.touristando

import android.app.ProgressDialog
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.brunoomcamara.touristando.Model.PontoTuristico
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {


    private var mMap: GoogleMap? = null

    private lateinit var latlng: LatLng

    var cidade = ""

    lateinit var progress: ProgressDialog

    private var strEnd = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        cidade = intent.extras.getString("cid")

        bottonNavViewMap.setOnNavigationItemSelectedListener {item ->

            if (item.itemId == R.id.itemMap) {
                Toast.makeText(this, "Lista de lugares encontrados", Toast.LENGTH_SHORT).show()

            } else if (item.itemId == R.id.itemListPontos) {
                itemLista()
            }
            true
        }

        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.setOnMapClickListener(this)

        mMap?.mapType = GoogleMap.MAP_TYPE_NORMAL

        val pontos:List<PontoTuristico> = SugarRecord.find(PontoTuristico::class.java, "cidade = ?", cidade)

        for (ponto in pontos) {

            val pontoEncontrado = LatLng(ponto.lat!!.toDouble(), ponto.log!!.toDouble())
            mMap?.addMarker(createMarkers(pontoEncontrado, ponto.nome, "nota: " + ponto.nota.toString()))
        }
        val camMap = LatLng(pontos[0].lat!!.toDouble(),pontos[0].log!!.toDouble())
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(camMap, 15f))
    }
    fun createMarkers(latLng: LatLng, title:String, snippet:String): MarkerOptions{
        return MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
    }
    override fun onMapClick(p0: LatLng?) {

    }

    fun itemLista(){

        var intent = Intent(this@MapsActivity, ListagemPontosActivity::class.java)
        intent.putExtra("cidade", cidade)
        startActivity(intent)
    }

}
