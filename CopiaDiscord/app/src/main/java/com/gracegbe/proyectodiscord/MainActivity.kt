package com.gracegbe.proyectodiscord

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    
    companion object{
        var i = 0
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listaSalas = ArrayList<String>()

        val recyclerViewSalas = findViewById<RecyclerView>(R.id.rcv_salas_discord)
        val crearSala = findViewById<Button>(R.id.btn_crear_sala)

        //crearSala.setTextColor(BackgroundColorSpan)

        crearSala.setOnClickListener {
            //var letraAux = letraSig.
            listaSalas.add(('A' + i).toString())
            i += 1
            iniciarRecyclerView(
                listaSalas,
                this,
                recyclerViewSalas
            )
        }

    }

    fun iniciarRecyclerView(
        lista: List<String>,
        actividad: MainActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = Salas(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun añadirLetra(lista: ArrayList<String>, letra: String){
        lista.add(letra);
    }


}