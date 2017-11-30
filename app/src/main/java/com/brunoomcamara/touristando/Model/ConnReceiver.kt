package com.brunoomcamara.touristando.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

import android.widget.Toast
class ConnReceiver : BroadcastReceiver() {

    var test:Boolean? = null
    //verifica se a internet esta ativa ou não
    override fun onReceive(context: Context, intent: Intent) {

        var cm: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager


        var netInfo = cm.activeNetworkInfo

        if (netInfo != null && netInfo.isConnectedOrConnecting) {
            Toast.makeText(context,"Conexão com internet ativada", Toast.LENGTH_SHORT).show()
        } else {
           Toast.makeText(context,"Conexão com internet desativada", Toast.LENGTH_SHORT).show()
        }
    }
}