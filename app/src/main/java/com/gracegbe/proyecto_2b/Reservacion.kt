package com.gracegbe.proyecto_2b

import java.util.*

class Reservacion (
    val nombreUsuarioCliente: String,
    val nombreUsuarioRestaurante: String,
    val fechaReservacion: String,
    val horaReservacion: String,
    val numeroPersonas: String,
    val estado: String
    )
{
    companion object{
        var listaReservacionesRestaurante = ArrayList<Reservacion>()
        var listaReservacionesCliente = ArrayList<Reservacion>()
    }

    override fun toString(): String {
        val cadena = "$nombreUsuarioCliente - $fechaReservacion - $horaReservacion - $numeroPersonas"
        return cadena
    }
}