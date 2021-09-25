package com.gracegbe.proyecto_2b

import java.util.*

class Restaurante (
    val nombreUsuarioRestaurante: String,
    val correoElectronicoRestaurante: String,
    val nombreRestaurante: String,
    val horarioAtencion: String,
    val numeroCelular: String,
    val direccionRestaurante: String,
    val descripcionRestaurante: String
    //val rol: String
        ) {

    companion object{
        var nombreUsuarioRestauranteAux = ""
        var correoElectronicoRestauranteAux = ""
        var nombreRestaurenteAux = ""
        var nombreRestauranteSeteado = "m"
        var nombreRestauranteSeleccionado = ""
        var listaRestaurante = ArrayList<Restaurante>()
    }

}