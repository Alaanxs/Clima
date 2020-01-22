package com.example.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var grados:TextView? = null
    var ciudad:TextView? = null
    var estatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grados = findViewById<TextView>(R.id.grados)
        ciudad = findViewById<TextView>(R.id.ciudad)
        estatus = findViewById<TextView>(R.id.estatus)

        val ciudades = intent.getStringExtra("com.example.clima.ciudades.CIUDAD")

        val ciudadmx = Ciudad("Ciudad de México", 25, "Nublado")
        val canada = Ciudad("Canada", 2, "Soleado")

        if(ciudades == "ciudad-mexico"){
            grados?.text = ciudadmx.grados.toString()+"°"
            ciudad?.text = ciudadmx.nombre
            estatus?.text = ciudadmx.estatus
        }
        else if(ciudades == "canada"){
            grados?.text = canada.grados.toString()+"°"
            ciudad?.text = canada.nombre
            estatus?.text = canada.estatus
        }
        else{
            Toast.makeText(this, "Ningun dato", Toast.LENGTH_LONG).show()
        }
    }
}
