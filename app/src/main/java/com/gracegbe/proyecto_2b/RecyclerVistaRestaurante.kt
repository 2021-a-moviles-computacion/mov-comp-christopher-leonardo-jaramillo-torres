package com.gracegbe.proyecto_2b

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File


val uri: Uri? = null
var taskSnapShopt: UploadTask.TaskSnapshot? = null
var mStorage: StorageReference? = null

class RecyclerVistaRestaurante (
    private val contexto: InicioCliente,
    private val listaRestaurantes: List<Restaurante>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<RecyclerVistaRestaurante.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val irVistaRestaurante: ImageButton
        val nombreRestaurante: TextView
        val horarioRestaurante: TextView
        val telefonoRestaurante: TextView
        val descripcionRestaruante: TextView
        val correo: TextView

        val contexto = view.context
        init {
            irVistaRestaurante = view.findViewById(R.id.btn_img_ir_restaurante)
            nombreRestaurante = view.findViewById(R.id.txtv_rec_nombre_restaurante)
            horarioRestaurante = view.findViewById(R.id.txtv_rec_horario_restaurante)
            telefonoRestaurante = view.findViewById(R.id.txtv_rec_celular_restaurante)
            descripcionRestaruante = view.findViewById(R.id.txtv_rec_descripcion_restaurante)
            correo = view.findViewById(R.id.txtv_rec_correo)

            irVistaRestaurante.setOnClickListener{
                //Restaurante.nombreRestaurenteAux = nombreRestaurante.text.toString()

                val intent = Intent(contexto, VistaRestaurante::class.java)

                Restaurante.listaRestaurante.forEach {
                    if(it.nombreRestaurante.equals(nombreRestaurante.text.toString())){
                        intent.putExtra("nombreRestaurante", it.nombreRestaurante)
                        intent.putExtra("horarioAtencion", it.horarioAtencion)
                        intent.putExtra("telefonoRestaurante", it.numeroCelular)
                        intent.putExtra("direccionRestaurante", it.direccionRestaurante)
                        intent.putExtra("descripcionRestaurante", it.descripcionRestaurante)
                        intent.putExtra("correoRestaurante", it.correoElectronicoRestaurante)
                    }
                }

                contexto.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_restaurantes,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    // setear datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val restaurante = listaRestaurantes[position]
        holder.nombreRestaurante.text  = restaurante.nombreRestaurante
        holder.horarioRestaurante.text = restaurante.horarioAtencion.toString()
        holder.telefonoRestaurante.text = restaurante.numeroCelular
        holder.descripcionRestaruante.text = restaurante.descripcionRestaurante
        holder.correo.text = restaurante.correoElectronicoRestaurante

        mStorage = FirebaseStorage.getInstance().getReference()
        var islandRef = mStorage!!.child(holder.correo.text.toString())

        val localFile = File.createTempFile(holder.correo.text.toString(), "jpg")

        // irVistaRestaurante
        Glide.with(contexto).load(holder.correo.text.toString()).into(holder.irVistaRestaurante)
    }

    override fun getItemCount(): Int {
        return listaRestaurantes.size
    }


}