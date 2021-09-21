package com.gracegbe.firebase01

class Producto {

    val nombre: String
    val precio: Double

    constructor(nombre: String, precio: Double){
        this.nombre = nombre
        this.precio = precio
    }

    override fun toString(): String {
        return "$nombre - $precio"
    }


}