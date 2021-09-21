package com.gracegbe.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.gracegbe.examen.BaseDatosServidor

class VerPaginas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_paginas)
        
        ServidorMemoria.baseDatos = BaseDatosServidor(this)
        ServidorMemoria.baseDatos?.recuperarPaginas()
       
       
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas)
        adaptador.notifyDataSetChanged()

        val listViewPaginas = findViewById<ListView>(R.id.ltv_paginas)

        listViewPaginas.adapter = adaptador

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
                
                val aux = ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas[info.position]
                ServidorMemoria.paginaActualizar = aux
                ServidorMemoria.actualizarPagina = true

                if (aux != null) {
                    //ServidorMemoria.servidorSelecionado = aux.ubicacion.toString()
                    ServidorMemoria.idPaginaArraySeleccionado = info.position
                }

                abrirActividad(InsertarPagina::class.java)

                return true
            }
            R.id.op_eliminar_pagina -> {

                ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas.removeAt(info.position)
                //ServidorMemoria.arregloServidores[info.position].paginas.clear()

                ServidorMemoria.baseDatos?.eliminarPagina(info.position, ServidorMemoria.idServidorArraySelecionado)

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