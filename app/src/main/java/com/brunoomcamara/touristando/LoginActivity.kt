package com.brunoomcamara.touristando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener {//clic butao login

            var checkBox = manter
            var inputUser = etUsuario.text //recebe usuario
            var inputSenha = etSenha.text // recebe senha

            if (inputUser.toString().equals("usuario") && inputSenha.toString().equals("123")) {//teste autenticação

                if(checkBox.isChecked){//metodo de verificar se checkbox esta selecionado
                    val prefs = getSharedPreferences("arquivo_de_preferencias", 0)// cria o arquivo de preferencia
                    val editor = prefs.edit()
                    editor.putBoolean("estaLogado", true)// seta como verdadeiro
                    editor.commit()//salva a preferencia no arquivo de preferencia
                }
                openHome()//chama metodo de abrir tela princial
            }
            else {
                alert("Usuário ou senha invalida")//exibe mensagem
            }
        }

    }

    private fun openHome() {//chama tela principal
        startActivity(Intent(this@LoginActivity, BuscaActivity::class.java))
    }

    private fun alert(a: String) {//metodo de mensagem
        Toast.makeText(this, a, Toast.LENGTH_LONG).show()
    }


}
