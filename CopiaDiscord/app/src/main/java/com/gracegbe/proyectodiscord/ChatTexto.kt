package com.gracegbe.proyectodiscord

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatTexto(
    private val contexto: ChatTextoVoz,
    private val listaChatT: List<String>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<ChatTexto.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val chat: Button
        val contexto = view.context
        
        init {
            chat = view.findViewById(R.id.btn_chat_texto)
        }

        fun setOnClickListener(){
            chat.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(contexto, VentanaChat::class.java)
            VentanaChat.nombreCabecera = chat.text.toString()
            contexto.startActivity(intent)
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatTexto.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.chattextodiscord,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatTexto.MyViewHolder, position: Int) {
        val chat = listaChatT[position]
        holder.chat.text = chat.toString()
        holder.setOnClickListener()
    }

    override fun getItemCount(): Int {
        return listaChatT.size
    }
    
}