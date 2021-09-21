package com.gracegbe.firebase01

class Orden {
    val restaurante: String
    val producto: String
    val cantidad: Int
    val subTotal: Double

    constructor(restaurante: String, producto: String, cantidad: Int, subTotal: Double){
        this.restaurante = restaurante
        this.producto = producto
        this.cantidad = cantidad
        this.subTotal = subTotal
    }

    override fun toString(): String {
        return "$restaurante - $producto - $cantidad - $subTotal"
    }

}