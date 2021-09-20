package com.gracegbe.proyectodiscord

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class Salas(
    private val contexto: MainActivity,
    private val listaSalas: List<String>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<Salas.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val sala: Button
        val contexto = view.context
        init {
            sala = view.findViewById(R.id.btn_rcv)
        }

        fun setOnClickListener(){
            sala.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            
            val intent = Intent(contexto, ChatTextoVoz::class.java)
            ChatTextoVoz.nombreCabeceraChaTextoVoz = sala.text.toString()
            MenuInferiorChatVoz.nombresala = sala.text.toString()
            contexto.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.iniciodiscord,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    // setear datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sala = listaSalas[position]
        holder.sala.text = sala.toString()
        holder.setOnClickListener()
        /*holder.sala.setOnClickListener(this)
        holder.contexto*/
    }

    override fun getItemCount(): Int {
        return listaSalas.size
    }
}