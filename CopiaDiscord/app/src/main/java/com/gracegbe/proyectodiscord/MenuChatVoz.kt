package com.gracegbe.proyectodiscord

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MenuChatVoz (
    private val contexto: MenuInferiorChatVoz,
    private val listaChatV: List<String>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<MenuChatVoz.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /*val chat: Button

        init {
            chat = view.findViewById(R.id.btn_chat_voz)
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuChatVoz.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.menuchatvoz,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaChatV.size
    }

    override fun onBindViewHolder(holder: MenuChatVoz.MyViewHolder, position: Int) {
        /*val chat = listaChatV[position]
        holder.chat.text = chat.toString()*/
    }
}