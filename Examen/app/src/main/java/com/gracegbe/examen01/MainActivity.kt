<<<<<<< HEAD
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.Servidor

class MainActivity : AppCompatActivity() {

    //var adaptador: ArrayAdapter<>()? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewServidores = findViewById<ListView>(R.id.ltv_servidores)
        recuperarServidores(listViewServidores)

        registerForContextMenu(listViewServidores)

        val botonIrAnadirServidor = findViewById<Button>(R.id.btn_ir_nuevo_servidor)
        botonIrAnadirServidor.setOnClickListener {
            abrirActividad(InsertarServido::class.java)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.op_editar_servidor -> {
                ServidorMemoria.actualizacionServidor = true
                ServidorMemoria.idServidorArraySelecionado = info.position
                abrirActividad(InsertarServido::class.java)

                return true
            }

            R.id.op_elliminar_servidor -> {

                val db = Firebase.firestore

                db.collection("servidor").document(ServidorMemoria.arregloServidores[info.position].toString())
                    .delete()

                val listViewServidores = findViewById<ListView>(R.id.ltv_servidores)
                recuperarServidores(listViewServidores)

                return true
            }

            R.id.op_ver_paginas -> {
                ServidorMemoria.idServidorArraySelecionado = info.position
                ServidorMemoria.servidorSelecionado = ServidorMemoria.arregloServidores[info.position].empresa.toString()
                abrirActividad(VerPaginas::class.java)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun arbriActividadConParamtetros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)

        //Esto permite enviar los parametros a la clase
        //Solo se pueden enviar variables primitivas
        // los parametros que se envian deben ser los mismos en el main y en la actividad creada
        intentExplicito.putExtra("nombre", "Leo")
        intentExplicito.putExtra("apellido", "Torres")
        intentExplicito.putExtra("edad", 23)
        /*intentExplicito.putExtra(
            "entrenador",
            BEntrenador("Chris","Jaramillo", )
        )*/
        startActivityForResult(intentExplicito,500)
    }

    fun recuperarServidores(listViewServidores: ListView){
        Log.i("store","entro a la recuperacion")
        val db = Firebase.firestore

        ServidorMemoria.arregloServidores.clear()

        db.collection("servidor")
            .get()
            .addOnSuccessListener {
                for (s in it){
                    Log.i("store"," -> ${s.id}")
                    val servidor = Servidor(
                        s.id.toString(),
                        s.getString("ubicacion"),
                        s.getString("direccionIP"),
                        s.getString("marca"),
                        s.getString("tipoServidor"),
                        s.getString("protocolos")
                    )
                    ServidorMemoria.arregloServidores.add(servidor)
                }

                val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ServidorMemoria.arregloServidores)
                adaptador.notifyDataSetChanged()
                listViewServidores.adapter = adaptador

                Log.i("store","tamaño antes de la funcion ${ServidorMemoria.arregloServidores.size}")

            }
            .addOnFailureListener {  }
    }

=======
<<<<<<< Updated upstream
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.gracegbe.examen.BaseDatosServidor

class MainActivity : AppCompatActivity() {

    //var adaptador: ArrayAdapter<>()? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ServidorMemoria.baseDatos = BaseDatosServidor(this)
        ServidorMemoria.baseDatos?.recuperarServidores()

        val botonIrAnadirServidor = findViewById<Button>(R.id.btn_ir_nuevo_servidor)
        botonIrAnadirServidor.setOnClickListener { 
            abrirActividad(InsertarServido::class.java)
        }
                
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ServidorMemoria.arregloServidores)
        adaptador.notifyDataSetChanged()

        val listViewServidores = findViewById<ListView>(R.id.ltv_servidores)

        listViewServidores.adapter = adaptador

        registerForContextMenu(listViewServidores)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.op_editar_servidor -> {
                        val aux = ServidorMemoria.arregloServidores[info.position]
                        ServidorMemoria.servidorActualizar = aux
                        ServidorMemoria.actualizacionServidor = true

                        Log.i("ser", "valor act : ${aux.ubicacion}")

                        if (aux != null) {
                            ServidorMemoria.servidorSelecionado = aux.ubicacion.toString()
                    ServidorMemoria.idServidorArraySelecionado = info.position
                }

                abrirActividad(InsertarServido::class.java)

                return true
            }
            R.id.op_elliminar_servidor -> {

                ServidorMemoria.arregloServidores[info.position].paginas.forEach{
                    ServidorMemoria.baseDatos?.eliminarPagina(it.id_pagina, info.position)
                }
                ServidorMemoria.arregloServidores[info.position].paginas.clear()

                ServidorMemoria.baseDatos?.eliminarServidor(info.position)
                ServidorMemoria.arregloServidores.removeAt(info.position)

                abrirActividad(MainActivity::class.java)

                return true
            }

            R.id.op_ver_paginas -> {
                //Log.i("ser", "valor act : ${ServidorMemoria.actualizacion}")

                abrirActividad(VerPaginas::class.java)
                
                val aux = ServidorMemoria.arregloServidores[info.position]
                //val auxI = ServidorMemoria.idServidorSelecionado
                
                if (aux != null) {
                    ServidorMemoria.servidorSelecionado = aux.ubicacion.toString()
                    ServidorMemoria.idServidorArraySelecionado = info.position
                }

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun arbriActividadConParamtetros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)

        //Esto permite enviar los parametros a la clase
        //Solo se pueden enviar variables primitivas
        // los parametros que se envian deben ser los mismos en el main y en la actividad creada
        intentExplicito.putExtra("nombre", "Leo")
        intentExplicito.putExtra("apellido", "Torres")
        intentExplicito.putExtra("edad", 23)
        /*intentExplicito.putExtra(
            "entrenador",
            BEntrenador("Chris","Jaramillo", )
        )*/
        startActivityForResult(intentExplicito,500)
    }
    
=======
package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.gracegbe.examen.BaseDatosServidor

class MainActivity : AppCompatActivity() {

    //var adaptador: ArrayAdapter<>()? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ServidorMemoria.baseDatos = BaseDatosServidor(this)
        ServidorMemoria.baseDatos?.recuperarServidores()

        val botonIrAnadirServidor = findViewById<Button>(R.id.btn_ir_nuevo_servidor)
        botonIrAnadirServidor.setOnClickListener { 
            abrirActividad(InsertarServido::class.java)
        }
                
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ServidorMemoria.arregloServidores)
        adaptador.notifyDataSetChanged()

        val listViewServidores = findViewById<ListView>(R.id.ltv_servidores)

        listViewServidores.adapter = adaptador

        registerForContextMenu(listViewServidores)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.op_editar_servidor -> {
                        val aux = ServidorMemoria.arregloServidores[info.position]
                        ServidorMemoria.servidorActualizar = aux
                        ServidorMemoria.actualizacionServidor = true

                        Log.i("ser", "valor act : ${aux.ubicacion}")

                        if (aux != null) {
                            ServidorMemoria.servidorSelecionado = aux.ubicacion.toString()
                    ServidorMemoria.idServidorArraySelecionado = info.position
                }

                abrirActividad(InsertarServido::class.java)

                return true
            }
            R.id.op_elliminar_servidor -> {

                ServidorMemoria.arregloServidores[info.position].paginas.forEach{
                    ServidorMemoria.baseDatos?.eliminarPagina(it.id_pagina, info.position)
                }
                ServidorMemoria.arregloServidores[info.position].paginas.clear()

                ServidorMemoria.baseDatos?.eliminarServidor(info.position)
                ServidorMemoria.arregloServidores.removeAt(info.position)

                abrirActividad(MainActivity::class.java)

                return true
            }

            R.id.op_ver_paginas -> {
                //Log.i("ser", "valor act : ${ServidorMemoria.actualizacion}")

                abrirActividad(VerPaginas::class.java)
                
                val aux = ServidorMemoria.arregloServidores[info.position]
                //val auxI = ServidorMemoria.idServidorSelecionado
                
                if (aux != null) {
                    ServidorMemoria.servidorSelecionado = aux.ubicacion.toString()
                    ServidorMemoria.idServidorArraySelecionado = info.position
                }

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    fun abrirActividad(clase: Class<*>){
        val intentImplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentImplicito)
    }

    fun arbriActividadConParamtetros(clase: Class<*>){
        val intentExplicito = Intent(this, clase)

        //Esto permite enviar los parametros a la clase
        //Solo se pueden enviar variables primitivas
        // los parametros que se envian deben ser los mismos en el main y en la actividad creada
        intentExplicito.putExtra("nombre", "Leo")
        intentExplicito.putExtra("apellido", "Torres")
        intentExplicito.putExtra("edad", 23)
        /*intentExplicito.putExtra(
            "entrenador",
            BEntrenador("Chris","Jaramillo", )
        )*/
        startActivityForResult(intentExplicito,500)
    }
    
>>>>>>> Stashed changes
>>>>>>> desarrollo
}