package com.gracegbe.examen2b

import PaginaWeb
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InsertarPagina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_pagina)
        val nombrePagina = findViewById<EditText>(R.id.txt_nombre)
        val nombreIndex = findViewById<EditText>(R.id.txt_nombreIndex)
        val autor = findViewById<EditText>(R.id.txt_autor)
        val framework = findViewById<EditText>(R.id.txt_framework)
        val lenguajes = findViewById<EditText>(R.id.txt_lenguajes)
        val latitud = findViewById<EditText>(R.id.txt_latitud)
        val longitud = findViewById<EditText>(R.id.txt_longitud)

        if (ServidorMemoria.actualizarPagina == true) {

            val db = Firebase.firestore

            db.collection("servidor")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                .collection("pagina")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre.toString())
                .get()
                .addOnSuccessListener {
                    var pagina =
                        PaginaWeb(
                            it.id.toString(),
                            it.getString("autor").toString(),
                            it.getString("framework").toString(),
                            it.getString("lenguajes").toString(),
                            it.getString("nombreIndex").toString(),
                            it.getString("latitud").toString(),
                            it.getString("longitud").toString()
                        )

                    nombrePagina.setText(pagina.nombre)
                    nombreIndex.setText(pagina.nombreIndex)
                    autor.setText(pagina.autor)
                    framework.setText(pagina.framework)
                    lenguajes.setText(pagina.lenguajes)
                    latitud.setText(pagina.latitud)
                    longitud.setText(pagina.longitud)

                }

        }

        val botonNuevaPagina = findViewById<Button>(R.id.btn_insertar_pagina)

        botonNuevaPagina.setOnClickListener {

            val datosPagina = hashMapOf<String, Any>(
                "nombreIndex" to nombreIndex.text.toString(),
                "autor" to autor.text.toString(),
                "framework" to framework.text.toString(),
                "lenguajes" to lenguajes.text.toString(),
                "latitud" to latitud.text.toString(),
                "longitud" to longitud.text.toString()
            )

            if (nombrePagina.text.isNotEmpty() &&
                    nombreIndex.text.isNotEmpty() &&
                    autor.text.isNotEmpty() &&
                    framework.text.isNotEmpty() &&
                    lenguajes.text.isNotEmpty() &&
                    latitud.text.isNotEmpty()){

                if (ServidorMemoria.actualizarPagina == false) {

                    val db = Firebase.firestore

                    val pagina = db.collection("servidor")
                            .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                            .collection("pagina")

                    pagina.document(nombrePagina.text.toString()).set(datosPagina)
                    abrirActividad(VerPaginas::class.java)
                }

                if (ServidorMemoria.actualizarPagina == true) {

                    val db = Firebase.firestore

                    val paginaAct = db.collection("servidor")
                            .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                            .collection("pagina")
                            .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre.toString())

                    paginaAct.update(datosPagina)
                    ServidorMemoria.actualizarPagina = false
                    abrirActividad(VerPaginas::class.java)

                }

            } else {
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Error")
                alerta.setMessage("LLene todos los campos")
                alerta.setPositiveButton("Aceptar", null)
                val dialog: AlertDialog = alerta.create()
                dialog.show()
            }
        }

    }


    fun abrirActividad(clase: Class<*>) {
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }
}