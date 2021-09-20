package com.gracegbe.examen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.gracegbe.examen01.ServidorMemoria

class BaseDatosServidor(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "Servidores",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {

        val scriptBaseServidores =
            """
                                              
            CREATE TABLE SERVIDOR(
            id_servidor INTEGER PRIMARY KEY,
            ubicacion VARCHAR(100) null,
            direccionIP VARCHAR(20) null,
            marca VARCHAR(50) null,
            empresa VARCHAR(100) null,
            tipoServidor VARCHAR(50) null,
            protocolos VARCHAR(200) null
            )
                       
                
            """.trimIndent()
        db?.execSQL(scriptBaseServidores)

        val scriptBasePaginas =
            """
                                         
           CREATE TABLE PAGINA (
            id_pagina INTEGER,
            id_servidor INTEGER,
            nombrePagina VARCHAR(50) null,
            nombreIndex VARCHAR(50) null,
            autor VARCHAR(50) null,
            frameworks VARCHAR(50) null,
            lenguajes VARCHAR(200) null,
            PRIMARY KEY (id_pagina, id_servidor),
            FOREIGN KEY (id_servidor) REFERENCES SERVIDOR (id_servidor)
            )
            
            """.trimIndent()
        db?.execSQL(scriptBasePaginas)

    }

  
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    
    fun insertarPagina(
        id_pagina: Int,
        id_servidor: Int,
        nombre: String,
        nombreIndex: String,
        autor: String,
        framework: String,
        lenguajes: String
    ){
        ServidorMemoria.indexPaginaUltimo =+ 1
        val id_paginaF = ServidorMemoria.indexPaginaUltimo
        val conexionEscritura = writableDatabase
        val valoresPagina = ContentValues()
        valoresPagina.put("id_pagina", id_pagina)
        valoresPagina.put("id_servidor", id_servidor)
        valoresPagina.put("nombrePagina", nombre)
        valoresPagina.put("nombreIndex", nombreIndex)
        valoresPagina.put("autor", autor)
        valoresPagina.put("frameworks", framework)
        valoresPagina.put("lenguajes", lenguajes)
        //val nuevoServidor = Servidor(ubicacion,direccionIP,marca,empresa,tipoServidor,protocolos)
        val resultados: Long = conexionEscritura.insert(
            "PAGINA",
            null,
            valoresPagina
        )
        //ServidorMemoria.arregloServidores.add(nuevoServidor)
        conexionEscritura.close()
    }

    fun recuperarPaginas(){/*
        //val consultarPaginas = "delete from PAGINA"
        val consultarPaginas = "select * from PAGINA where id_servidor = ${ServidorMemoria.idServidorArraySelecionado}"
        val lectura = readableDatabase
        val resultados = lectura.rawQuery(consultarPaginas, null)
        //val paginasEncontradas = PaginaWeb(0,0,"","","","","")
        val paginaExiste = resultados.moveToFirst()

        if(paginaExiste){
            var valor = 0
            //ServidorMemoria.indexPaginaUltimo = 0
            ServidorMemoria.arregloServidores[ServidorMemoria.idServidorArraySelecionado].paginas = ArrayList<PaginaWeb>()
            do{
                val id_pagina = resultados.getInt(0)
                val id_servidor = resultados.getInt(1)
                val nombrePagina = resultados.getString(2)
                val nombreIndex = resultados.getString(3)
                val autor = resultados.getString(4)
                val frameworks = resultados.getString(5)
                val lenguajes = resultados.getString(6)

                if ((id_pagina) != null) {

                    ServidorMemoria.arregloServidores[id_servidor].paginas?.add(
                        PaginaWeb(id_pagina, id_servidor, nombrePagina, nombreIndex, autor, frameworks, lenguajes))

                    Log.i("pagina", "index pagina ${id_pagina} - index servidor ${id_servidor}")
                    valor += 1
                }

                Log.i("pagina", "valor del index ${ServidorMemoria.indexPaginaUltimo} ")
            }while(resultados.moveToNext())

            //ServidorMemoria.indexPaginaUltimo += valor
        }

        resultados.close()
        lectura.close()*/

    }
    

    fun insertarServidor(
        id_servidor: Int,
        ubicacion: String,
        direccionIP: String,
        marca: String,
        empresa: String,
        tipoServidor: String,
        protocolos: String
    ){
        //ServidorMemoria.arregloServidores.add(Servidor(ubicacion,direccionIP,marca,empresa,tipoServidor,protocolos))
        //Log.i("crear", "insertar - tamaño del array ${ServidorMemoria.arregloServidores.size}")
       /* val conexionEscritura = writableDatabase
        val valoresServidor = ContentValues()
        valoresServidor.put("id_servidor", id_servidor)
        valoresServidor.put("ubicacion", ubicacion)
        valoresServidor.put("direccionIP", direccionIP)
        valoresServidor.put("marca", marca)
        valoresServidor.put("empresa", empresa)
        valoresServidor.put("tipoServidor", tipoServidor)
        valoresServidor.put("protocolos", protocolos)
        //val nuevoServidor = Servidor(ubicacion,direccionIP,marca,empresa,tipoServidor,protocolos)
        val resultados: Long = conexionEscritura.insert(
            "SERVIDOR",
            null,
            valoresServidor
        )
        //ServidorMemoria.arregloServidores.add(nuevoServidor)
        //Log.i("crear", "insertar - tamaño del array ${ServidorMemoria.arregloServidores.size}")
        conexionEscritura.close()
        //return nuevoServidor*/
    }
    
    fun recuperarServidores(){
        /*val consultarServidores = "select * from SERVIDOR"
        val lectura = readableDatabase
        val resultados = lectura.rawQuery(consultarServidores, null)
        val servidoresEncontrados = Servidor(0,"","","","","","")
        val servidorExiste = resultados.moveToFirst()

        if(servidorExiste){
            //var valor = 0
                //ServidorMemoria.indexBase = Se
            ServidorMemoria.arregloServidores = ArrayList<Servidor>()
            do{
                val id = resultados.getInt(0)
                val ubicacion = resultados.getString(1)
                val direccionIP = resultados.getString(2)
                val marca = resultados.getString(3)
                val empresa = resultados.getString(4)
                val tipoServidor = resultados.getString(5)
                val protocolos = resultados.getString(6)

                if ((id+1) != null) {
                    /*servidoresEncontrados.ubicacion = ubicacion
                    servidoresEncontrados.direccionIP = direccionIP
                    servidoresEncontrados.marca = direccionIP
                    servidoresEncontrados.empresa = empresa
                    servidoresEncontrados.tipoServidor = tipoServidor
                    servidoresEncontrados.protocolos = protocolos*/

                    ServidorMemoria.arregloServidores.add(Servidor(id, ubicacion,direccionIP,marca,empresa,tipoServidor,protocolos))
                    Log.i("servidor", "index servidor ${id}")
                    //Log.i("crear", "esta entreando al if interno ${id}")
                    //valor += 1
                }

            }while(resultados.moveToNext())
        }

        resultados.close()
        lectura.close()*/

    }

    fun actualizarServidor(
        id_servidor: Int,
        ubicacion: String,
        direccionIP: String,
        marca: String,
        empresa: String,
        tipoServidor: String,
        protocolos: String
    ): Boolean{
        val conexion = writableDatabase
        val valoresServidor = ContentValues()
        val servidorAux = ServidorMemoria
        valoresServidor.put("id_servidor", id_servidor)
        valoresServidor.put("ubicacion", ubicacion)
        valoresServidor.put("direccionIP", direccionIP)
        valoresServidor.put("marca", marca)
        valoresServidor.put("empresa", empresa)
        valoresServidor.put("tipoServidor", tipoServidor)
        valoresServidor.put("protocolos", protocolos)

        val resultadoConsulta = conexion.update(
            "SERVIDOR",
            valoresServidor,
            "id_servidor = ${id_servidor}",
            null
        )
        conexion.close()
        return if(resultadoConsulta == -1) false else true
    }

    fun actualizarPagina(
        id_pagina: Int,
        id_servidor: Int,
        nombre: String,
        nombreIndex: String,
        autor: String,
        framework: String,
        lenguajes: String
    ): Boolean{
        val conexion = writableDatabase
        val valoresPagina = ContentValues()
        val servidorAux = ServidorMemoria
        val conexionEscritura = writableDatabase
        //val valoresServidor = ContentValues()
        valoresPagina.put("id_pagina", id_pagina)
        valoresPagina.put("id_servidor", id_servidor)
        valoresPagina.put("nombrePagina", nombre)
        valoresPagina.put("nombreIndex", nombreIndex)
        valoresPagina.put("autor", autor)
        valoresPagina.put("frameworks", framework)
        valoresPagina.put("lenguajes", lenguajes)

        val resultadoConsulta = conexion.update(
            "PAGINA",
            valoresPagina,
            "id_pagina = ${id_pagina} and id_servidor = ${id_servidor}",
            null
        )
        conexion.close()
                
        return if(resultadoConsulta == -1) false else true
    }
    
    fun eliminarServidor(id_servidor: Int){
        val conexcion = writableDatabase
        val eliminacionServidor = conexcion.delete(
                "SERVIDOR",
                "id_servidor = ${id_servidor}",
                null
        )
    }

    fun eliminarPagina(id_pagina: Int, id_servidor: Int){
        val conexcion = writableDatabase
        val eliminacionServidor = conexcion.delete(
                "PAGINA",
                "id_pagina = ${id_pagina} and id_servidor = ${id_servidor}",
                null
        )
        ServidorMemoria.indexPaginaUltimo -= 1
    }
    
}

