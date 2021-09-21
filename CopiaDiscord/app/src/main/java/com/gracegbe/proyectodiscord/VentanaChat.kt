package com.gracegbe.proyectodiscord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class VentanaChat : AppCompatActivity() {

    companion object{
        var nombreCabecera: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ventana_chat_texto)
        
        val nombreCabeceraText = findViewById<TextView>(R.id.txt_nombre_cabecera)
        
        nombreCabeceraText.setText(nombreCabecera)
        
        val mensajes = findViewById<TextView>(R.id.input_mensajes)
        
        mensajes.setText("Enviar mensajes a ${nombreCabecera}")

        val intengo = findViewById<ImageButton>(R.id.imageButton2)
        

    }
}