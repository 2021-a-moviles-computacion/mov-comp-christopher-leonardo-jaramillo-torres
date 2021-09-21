package com.gracegbe.examen

import android.widget.ArrayAdapter
import java.util.*

class PaginaWeb (
    var id_pagina: Int,
    var id_servidor: Int,
    var nombre: String,
    var nombreIndex: String,
    var autor: String,
    var framework: String,
    var lenguajes: String
){
    override fun toString():String{
        return "${nombre}"
    }
