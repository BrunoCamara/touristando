package com.brunoomcamara.touristando

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler



class SplashActivity : AppCompatActivity() {
    private val Splash_screen=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        var prefs = getSharedPreferences("arquivo_de_preferencias", Context.MODE_PRIVATE)
        var jaLogou = prefs.getBoolean("estaLogado",false)

        Handler().postDelayed({

            if (jaLogou){
                startActivity(Intent(this, NavigationActivity::class.java))
             }else{
                startActivity(Intent(this, LoginActivity::class.java))
        }},Splash_screen.toLong())
    }
}
