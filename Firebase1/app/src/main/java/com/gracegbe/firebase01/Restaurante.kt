package com.gracegbe.firebase01

class Restaurante {
    val nombre: String

    constructor(nombre: String){
        this.nombre = nombre
    }

    override fun toString(): String {
        return nombre
    }

}