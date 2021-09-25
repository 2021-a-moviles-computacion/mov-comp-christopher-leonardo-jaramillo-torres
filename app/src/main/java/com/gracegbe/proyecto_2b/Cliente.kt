package com.gracegbe.proyecto_2b

import java.util.*

class Cliente (
    val nombreUsuarioRestaurante: String,
    val correoElectronicoRestaurante: String,
    val nombreCliente: String,
    val fechaNacimientoCCliente: Date,
    val descripcionCliente: String
        ){

    companion object{
        var nombreUsuarioClienteAux = ""
        var correoElectronicoClienteAux = ""
        var usuarioNombreClienteSeteado = "a"
    }
}