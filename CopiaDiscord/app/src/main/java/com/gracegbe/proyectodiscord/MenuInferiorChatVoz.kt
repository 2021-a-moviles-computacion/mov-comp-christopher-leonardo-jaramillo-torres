package com.gracegbe.proyectodiscord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuInferiorChatVoz : AppCompatActivity() {

    companion object{
        var nombrechat: String? = null
        var nombresala: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_inferior_chat_voz)


        val lista = ArrayList<String>()
        lista.add("A")
        val recyclerView = findViewById<RecyclerView>(R.id.rcv_menu_inferior)
        
        iniciarRecyclerView(
                lista,
                this,
                recyclerView
            )
    }

    

fun iniciarRecyclerView(
    lista: List<String>,
    actividad: MenuInferiorChatVoz,
    recyclerView: RecyclerView
){
    val adaptador = MenuChatVoz(
        actividad,
        lista,
        recyclerView
    )
    recyclerView.adapter = adaptador
    recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
    adaptador.notifyDataSetChanged()
}
    
}