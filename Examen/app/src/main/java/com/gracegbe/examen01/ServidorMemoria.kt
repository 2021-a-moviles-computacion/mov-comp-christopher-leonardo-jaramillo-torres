<<<<<<< Updated upstream
package com.gracegbe.examen01

import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.PaginaWeb
import com.gracegbe.examen.Servidor

class ServidorMemoria {

    companion object{
        var arregloServidores = ArrayList<Servidor>()
        var arregloPaginas = ArrayList<PaginaWeb>()
        var actualizacionServidor = false
        var actualizarPagina = false
        var servidorActualizar: Servidor? = null
        var paginaActualizar: PaginaWeb? = null
        var baseDatos: BaseDatosServidor? = null
        var servidorSelecionado = ""
        var idServidorArraySelecionado = 0
        var idPaginaArraySeleccionado = 0
        var indexBase = 0
        var indexPaginaUltimo = 0
    }
    
=======
package com.gracegbe.examen01

import com.gracegbe.examen.BaseDatosServidor
import com.gracegbe.examen.PaginaWeb
import com.gracegbe.examen.Servidor

class ServidorMemoria {

    companion object{
        var arregloServidores = ArrayList<Servidor>()
        var arregloPaginas = ArrayList<PaginaWeb>()
        var actualizacionServidor = false
        var actualizarPagina = false
        var servidorActualizar: Servidor? = null
        var paginaActualizar: PaginaWeb? = null
        var baseDatos: BaseDatosServidor? = null
        var servidorSelecionado = ""
        var idServidorArraySelecionado = 0
        var idPaginaArraySeleccionado = 0
        var indexBase = 0
        var indexPaginaUltimo = 0
    }
    
>>>>>>> Stashed changes
}