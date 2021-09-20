package com.gracegbe.proyectodiscord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatTextoVoz : AppCompatActivity() {

    companion object{
        var i = 0
        var j = 0
        var nombreCabeceraChaTextoVoz: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        i = 0
        j = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_discord)

        val listaChatTexto = ArrayList<String>()
        val listaChatVoz = ArrayList<String>()

        val recyclerViewChatText = findViewById<RecyclerView>(R.id.rcv_chats_texto)
        val recyclerViewChatVoz = findViewById<RecyclerView>(R.id.rcv_chats_voz)

        val nombreCabeceraSala = findViewById<TextView>(R.id.txt_cabecera_salas)
        nombreCabeceraSala.setText(nombreCabeceraChaTextoVoz)

        val crearChatTextp = findViewById<Button>(R.id.btn_crear_chat_texto)

        crearChatTextp.setOnClickListener {
            var letraAuxT = ('A' + i).toString()
            listaChatTexto.add("# ${letraAuxT}")
            i += 1
            iniciarRecyclerViewTexto(
                listaChatTexto,
                this,
                recyclerViewChatText
            )
        }

        val crearChatVoz = findViewById<Button>(R.id.btn_ccrear_chat_voz)

        crearChatVoz.setOnClickListener {
            var letraAuxP = ('A' + j).toString()
            listaChatVoz.add("${letraAuxP}")
            j += 1
            iniciarRecyclerViewVoz(
                listaChatVoz,
                this,
                recyclerViewChatVoz
            )
        }

    }

    fun iniciarRecyclerViewTexto(
        lista: List<String>,
        actividad: ChatTextoVoz,
        recyclerView: RecyclerView
    ){
        val adaptador = ChatTexto(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

    fun iniciarRecyclerViewVoz(
        lista: List<String>,
        actividad: ChatTextoVoz,
        recyclerView: RecyclerView
    ){
        val adaptador = ChatVoz(
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