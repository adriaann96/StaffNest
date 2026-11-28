package com.example.staffnest.model

class InicioSesion(
    val correo: String,
    val password: String
)

class TokenSesion(
    val access_token: String,
    val token_type: String,
    val expires_at: String
)

data class UsuarioSesion(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val rol: String,
    val estatus: String,
    val estancia_id: Int?
)