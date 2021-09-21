package com.gracegbe.examen

import PaginaWeb
import java.util.*

class Servidor (
    //var id_servidor: Int?,
    var empresa: String?,
    var ubicacion: String?,
    var direccionIP: String?,
    var marca: String?,
    var tipoServidor: String?,
    var protocolos: String?
) {

    var paginas = ArrayList<PaginaWeb>()

    override fun toString():String{
        return "${empresa}"
    }
}

