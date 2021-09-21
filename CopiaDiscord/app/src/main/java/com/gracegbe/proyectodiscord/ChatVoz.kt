package com.gracegbe.proyectodiscord

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatVoz(
    private val contexto: ChatTextoVoz,
    private val listaChatV: List<String>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<ChatVoz.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val chat: Button
        val contexto = view.context

        init {
            chat = view.findViewById(R.id.btn_chat_voz)
        }

        fun setOnClickListener(){
            chat.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(contexto, MenuInferiorChatVoz::class.java)
            //MenuInferiorChatVoz.nombrechat = "--"
            //VentanaChat.nombreCabecera = ('A' + ChatTextoVoz.i - 1).toString()
            contexto.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatVoz.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.chatvozdiscord,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaChatV.size
    }

    override fun onBindViewHolder(holder: ChatVoz.MyViewHolder, position: Int) {
        val chat = listaChatV[position]
        holder.chat.text = chat.toString()
        holder.setOnClickListener()
    }
    
}