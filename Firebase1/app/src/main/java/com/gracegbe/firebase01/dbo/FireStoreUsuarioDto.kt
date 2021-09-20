package com.gracegbe.firebase01.dbo

data class FireStoreUsuarioDto(
    var uid: String ="",
    var email: String = "",
    var roles: ArrayList<String> = arrayListOf()
) {
}