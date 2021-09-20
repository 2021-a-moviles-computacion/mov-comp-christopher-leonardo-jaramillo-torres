<<<<<<< HEAD
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.PaginaWeb
import com.gracegbe.examen.Servidor

class InsertarPagina : AppCompatActivity() {

    var paginaAux: PaginaWeb?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_pagina)

        val nombrePagina  = findViewById<EditText>(R.id.txt_nombre)
        val nombreIndex = findViewById<EditText>(R.id.txt_nombreIndex)
        val autor = findViewById<EditText>(R.id.txt_autor)
        val framework = findViewById<EditText>(R.id.txt_framework)
        val lenguajes = findViewById<EditText>(R.id.txt_lenguajes)
        val latitud = findViewById<EditText>(R.id.txt_latitud)
        val longitud = findViewById<EditText>(R.id.txt_longitud)

        if(ServidorMemoria.actualizarPagina == true) {

            val db = Firebase.firestore

            db.collection("servidor")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                .collection("pagina")
                .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre.toString())
                .get()
                .addOnSuccessListener {
                    var pagina =
                        PaginaWeb(it.id.toString(),
                            it.getString("autor").toString(),
                            it.getString("framework").toString(),
                            it.getString("lenguajes").toString(),
                            it.getString("nombreIndex").toString(),
                            it.getString("latitud").toString(),
                            it.getString("longitud").toString())

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

            if(ServidorMemoria.actualizarPagina == false){

                val db = Firebase.firestore

                val pagina = db.collection("servidor")
                    .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                    .collection("pagina")

                pagina.document(nombrePagina.text.toString()).set(datosPagina)
                abrirActividad(VerPaginas::class.java)
            }

            if (ServidorMemoria.actualizarPagina == true){

                val db = Firebase.firestore

                val paginaAct = db.collection("servidor")
                    .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].empresa.toString())
                    .collection("pagina")
                    .document(ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre.toString())

                paginaAct.update(datosPagina)
                ServidorMemoria.actualizarPagina = false
                abrirActividad(VerPaginas::class.java)

            }
        }

    }


    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

=======
<<<<<<< Updated upstream
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.PaginaWeb

class InsertarPagina : AppCompatActivity() {

    var paginaAux: PaginaWeb?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_pagina)

        ServidorMemoria.baseDatos = BaseDatosServidor(this)

        val id_servidor = ServidorMemoria.idServidorArraySelecionado
        val id_pagina = ServidorMemoria.arregloServidores[id_servidor].paginas.size
        Log.i("asc","------> ${id_pagina}")
        val nombrePagina  = findViewById<EditText>(R.id.txt_nombre)
        val nombreIndex = findViewById<EditText>(R.id.txt_nombreIndex)
        val autor = findViewById<EditText>(R.id.txt_autor)
        val framework = findViewById<EditText>(R.id.txt_framework)
        val lenguajes = findViewById<EditText>(R.id.txt_lenguajes)

        if(ServidorMemoria.actualizarPagina == true) {
            nombrePagina.setText(ServidorMemoria.paginaActualizar?.nombre)
            nombreIndex.setText(ServidorMemoria.paginaActualizar?.nombreIndex)
            autor.setText(ServidorMemoria.paginaActualizar?.autor)
            framework.setText(ServidorMemoria.paginaActualizar?.framework)
            lenguajes.setText(ServidorMemoria.paginaActualizar?.lenguajes)

        }

        val botonNuevaPagina = findViewById<Button>(R.id.btn_insertar_pagina)

        botonNuevaPagina.setOnClickListener {
            Log.i("ver","-> ${ServidorMemoria.actualizarPagina}")
            if(ServidorMemoria.actualizarPagina == false){

                Log.i("ver", "entro al primer if inserta idpagina ${ServidorMemoria.idPaginaArraySeleccionado} - idservidor ${ServidorMemoria.idServidorArraySelecionado}")
                if(ServidorMemoria != null){
                    if (id_pagina != null) {
                        ServidorMemoria.baseDatos?.insertarPagina(
                                id_pagina.toInt(),
                                id_servidor.toInt(),
                                nombrePagina.text.toString(),
                                nombreIndex.text.toString(),
                                autor.text.toString(),
                                framework.text.toString(),
                                lenguajes.text.toString()
                        )
                    }

                    nombrePagina.setText("")
                    nombreIndex.setText("")
                    autor.setText("")
                    framework.setText("")
                    lenguajes.setText("")
                }
                abrirActividad(VerPaginas::class.java)
            }

            if (ServidorMemoria.actualizarPagina == true){
                Log.i("ver", "entro al segundo if solo actualiza idpagina ${ServidorMemoria.idPaginaArraySeleccionado} - idservidor ${ServidorMemoria.idServidorArraySelecionado}")

                val salio = ServidorMemoria.baseDatos?.actualizarPagina(
                        ServidorMemoria.idPaginaArraySeleccionado,
                        ServidorMemoria.idServidorArraySelecionado,
                        nombrePagina.text.toString(),
                        nombreIndex.text.toString(),
                        autor.text.toString(),
                        framework.text.toString(),
                        lenguajes.text.toString()
                )

                //Log.i("act", "salio -> ${salio}")

                paginaAux = PaginaWeb(ServidorMemoria.idPaginaArraySeleccionado, ServidorMemoria.idServidorArraySelecionado, nombrePagina.text.toString(), nombreIndex.text.toString(),
                        autor.text.toString(), framework.text.toString(), lenguajes.text.toString())

                actualizarDatosPagina(paginaAux)
                abrirActividad(VerPaginas::class.java)
                ServidorMemoria.actualizarPagina = false
            }
        }

    }



    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun actualizarDatosPagina(paginaAct: PaginaWeb?){
        if (paginaAct != null) {
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].id_servidor = paginaAct.id_servidor
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre = paginaAct.nombre
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombreIndex = paginaAct.nombre
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].autor = paginaAct.autor
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].framework = paginaAct.framework
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].lenguajes = paginaAct.lenguajes
        }

    }


=======
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.PaginaWeb

class InsertarPagina : AppCompatActivity() {

    var paginaAux: PaginaWeb?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_pagina)

        ServidorMemoria.baseDatos = BaseDatosServidor(this)

        val id_servidor = ServidorMemoria.idServidorArraySelecionado
        val id_pagina = ServidorMemoria.arregloServidores[id_servidor].paginas.size
        Log.i("asc","------> ${id_pagina}")
        val nombrePagina  = findViewById<EditText>(R.id.txt_nombre)
        val nombreIndex = findViewById<EditText>(R.id.txt_nombreIndex)
        val autor = findViewById<EditText>(R.id.txt_autor)
        val framework = findViewById<EditText>(R.id.txt_framework)
        val lenguajes = findViewById<EditText>(R.id.txt_lenguajes)

        if(ServidorMemoria.actualizarPagina == true) {
            nombrePagina.setText(ServidorMemoria.paginaActualizar?.nombre)
            nombreIndex.setText(ServidorMemoria.paginaActualizar?.nombreIndex)
            autor.setText(ServidorMemoria.paginaActualizar?.autor)
            framework.setText(ServidorMemoria.paginaActualizar?.framework)
            lenguajes.setText(ServidorMemoria.paginaActualizar?.lenguajes)

        }

        val botonNuevaPagina = findViewById<Button>(R.id.btn_insertar_pagina)

        botonNuevaPagina.setOnClickListener {
            Log.i("ver","-> ${ServidorMemoria.actualizarPagina}")
            if(ServidorMemoria.actualizarPagina == false){

                Log.i("ver", "entro al primer if inserta idpagina ${ServidorMemoria.idPaginaArraySeleccionado} - idservidor ${ServidorMemoria.idServidorArraySelecionado}")
                if(ServidorMemoria != null){
                    if (id_pagina != null) {
                        ServidorMemoria.baseDatos?.insertarPagina(
                                id_pagina.toInt(),
                                id_servidor.toInt(),
                                nombrePagina.text.toString(),
                                nombreIndex.text.toString(),
                                autor.text.toString(),
                                framework.text.toString(),
                                lenguajes.text.toString()
                        )
                    }

                    nombrePagina.setText("")
                    nombreIndex.setText("")
                    autor.setText("")
                    framework.setText("")
                    lenguajes.setText("")
                }
                abrirActividad(VerPaginas::class.java)
            }

            if (ServidorMemoria.actualizarPagina == true){
                Log.i("ver", "entro al segundo if solo actualiza idpagina ${ServidorMemoria.idPaginaArraySeleccionado} - idservidor ${ServidorMemoria.idServidorArraySelecionado}")

                val salio = ServidorMemoria.baseDatos?.actualizarPagina(
                        ServidorMemoria.idPaginaArraySeleccionado,
                        ServidorMemoria.idServidorArraySelecionado,
                        nombrePagina.text.toString(),
                        nombreIndex.text.toString(),
                        autor.text.toString(),
                        framework.text.toString(),
                        lenguajes.text.toString()
                )

                //Log.i("act", "salio -> ${salio}")

                paginaAux = PaginaWeb(ServidorMemoria.idPaginaArraySeleccionado, ServidorMemoria.idServidorArraySelecionado, nombrePagina.text.toString(), nombreIndex.text.toString(),
                        autor.text.toString(), framework.text.toString(), lenguajes.text.toString())

                actualizarDatosPagina(paginaAux)
                abrirActividad(VerPaginas::class.java)
                ServidorMemoria.actualizarPagina = false
            }
        }

    }



    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun actualizarDatosPagina(paginaAct: PaginaWeb?){
        if (paginaAct != null) {
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].id_servidor = paginaAct.id_servidor
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombre = paginaAct.nombre
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].nombreIndex = paginaAct.nombre
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].autor = paginaAct.autor
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].framework = paginaAct.framework
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[ServidorMemoria.idPaginaArraySeleccionado].lenguajes = paginaAct.lenguajes
        }

    }


>>>>>>> Stashed changes
>>>>>>> desarrollo
}