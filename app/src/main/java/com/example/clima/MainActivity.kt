package com.example.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var grados:TextView? = null
    var ciudadd:TextView? = null
    var estatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grados = findViewById<TextView>(R.id.grados)
        ciudadd = findViewById<TextView>(R.id.ciudad)
        estatus = findViewById<TextView>(R.id.estatus)

        val ciudad = intent.getStringExtra("com.example.clima.ciudades.CIUDAD")

        if(Network.hayRed(this)){
            solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&appid=e4b72ea7c43b8c4ad676e5def05848fd&units=metric&lang=es")
        }
        else{
            Toast.makeText(this, "No hay internet", Toast.LENGTH_LONG).show()
        }
    }

    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest( Request.Method.GET, url,
            Response.Listener<String> { response ->
                try {
                    Log.d("solicitudHTTPVolley", response)

                    val gson = Gson()
                    val ciudad = gson.fromJson(response, Ciudad::class.java)
                    Log.d("GSON", ciudad.name)
                    grados?.text = ciudad.main?.temp?.toString()+"°"
                    ciudadd?.text = ciudad.name
                    estatus?.text = ciudad.weather?.get(0)?.description
                }catch (e: Exception){

                }
            },
            Response.ErrorListener {  })

        queue.add(stringRequest)
    }
}
