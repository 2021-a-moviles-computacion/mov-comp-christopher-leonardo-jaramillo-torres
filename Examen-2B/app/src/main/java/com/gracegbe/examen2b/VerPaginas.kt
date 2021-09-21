package com.gracegbe.examen2b

import PaginaWeb
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VerPaginas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_paginas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas
        )
        adaptador.notifyDataSetChanged()

        val listViewPaginas = findViewById<ListView>(R.id.ltv_paginas)
        recuperarPaginas(listViewPaginas)
        registerForContextMenu(listViewPaginas)

        val nombreServidor = findViewById<TextView>(R.id.txt_ubicacion_servidor)
        nombreServidor.setText(ServidorMemoria.servidorSelecionado)

        val regresarInicio = findViewById<Button>(R.id.btn_volver_inicio_servidor)
        regresarInicio.setOnClickListener {
            abrirActividad(MainActivity::class.java)
        }

        val botonIrNuevaPagina = findViewById<Button>(R.id.btn_ir_nueva_pagina)

        botonIrNuevaPagina.setOnClickListener {
            abrirActividad(InsertarPagina::class.java)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.op_editar_pagina -> {

                ServidorMemoria.actualizarPagina = true
                ServidorMemoria.idPaginaArraySeleccionado = info.position

                abrirActividad(InsertarPagina::class.java)

                return true
            }
            R.id.op_eliminar_pagina -> {

                val db = Firebase.firestore

                db.collection("servidor")
                    .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                    .collection("pagina")
                    .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[info.position].nombre.toString())
                    .delete()

                val listViewPagina = findViewById<ListView>(R.id.ltv_paginas)
                recuperarPaginas(listViewPagina)
                return true
            }

            R.id.op_ir_mapa -> {

                ServidorMemoria.idPaginaArraySeleccionado = info.position

                abrirActividad(UbicacionPagina::class.java)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    fun abrirActividad(clase: Class<*>) {
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun recuperarPaginas(listViewPaginas: ListView) {
        Log.i("store", "entro a la recuperacion")
        val db = Firebase.firestore

        ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas.clear()

        db.collection("servidor")
            .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
            .collection("pagina")
            .get()
            .addOnSuccessListener {
                for (p in it) {
                    val pagina =
                        PaginaWeb(
                            p.id.toString(),
                            p.getString("autor").toString(),
                            p.getString("framework").toString(),
                            p.getString("lenguajes").toString(),
                            p.getString("nombreIndex").toString(),
                            p.getString("latitud").toString(),
                            p.getString("longitud").toString()
                        )
                    ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas.add(
                        pagina
                    )
                }
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas
                )
                adaptador.notifyDataSetChanged()
                listViewPaginas.adapter = adaptador
            }
    }

}