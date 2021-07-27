<<<<<<< Updated upstream
package com.gracegbe.examen

import java.util.*

class Servidor (
    var id_servidor: Int?,
    var ubicacion: String?,
    var direccionIP: String?,
    var marca: String?,
    var empresa: String?,
    var tipoServidor: String?,
    var protocolos: String?
) {

    var paginas = ArrayList<PaginaWeb>()

    override fun toString():String{
        return "${ubicacion}"
    }
}

=======
package com.gracegbe.examen

import java.util.*

class Servidor (
    var id_servidor: Int?,
    var ubicacion: String?,
    var direccionIP: String?,
    var marca: String?,
    var empresa: String?,
    var tipoServidor: String?,
    var protocolos: String?
) {

    var paginas = ArrayList<PaginaWeb>()

    override fun toString():String{
        return "${ubicacion}"
    }
}

>>>>>>> Stashed changes
/*ubicacion, direccionIP, marca, empresa, tipoServidor, protocolos*/