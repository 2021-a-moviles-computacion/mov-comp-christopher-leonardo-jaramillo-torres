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
}