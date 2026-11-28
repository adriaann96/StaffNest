package com.example.staffnest.network

import com.example.staffnest.model.TokenSesion
import com.example.staffnest.model.UsuarioSesion
import java.time.Instant
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object SesionActual {

    private var token: TokenSesion? = null

    var usuario by mutableStateOf<UsuarioSesion?>(null)
        private set

    fun guardar(
        nuevoToken: TokenSesion,
        nuevoUsuario: UsuarioSesion
    ) {
        token = nuevoToken
        usuario = nuevoUsuario
    }

    fun obtenerToken(): String? {
        val actual = token ?: return null

        val vigente = runCatching {
            Instant.parse(actual.expires_at)
                .isAfter(Instant.now())
        }.getOrDefault(false)

        if (!vigente) {
            limpiar()
            return null
        }

        return "Bearer ${actual.access_token}"
    }

    fun limpiar() {
        token = null
        usuario = null
    }

}