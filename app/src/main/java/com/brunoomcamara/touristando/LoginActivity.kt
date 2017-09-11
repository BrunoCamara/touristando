package com.brunoomcamara.touristando

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import android.text.method.LinkMovementMethod
import android.widget.TextView





class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener {

            var inputUser = etUsuario.text
            var inputSenha = etSenha.text

            if (inputUser.toString().equals("bruno") && inputSenha.toString().equals("123")) {
                openHome()
            }
            else {
                alert("Usuário ou senha invalida")
            }
        }

    }

    private fun openHome() {
        startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))
    }

    private fun alert(a: String) {
        Toast.makeText(this, a, Toast.LENGTH_LONG).show()
    }


}
