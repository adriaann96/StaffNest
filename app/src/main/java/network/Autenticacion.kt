package com.example.staffnest.network

import com.example.staffnest.model.InicioSesion
import com.example.staffnest.model.Perfil
import kotlinx.coroutines.CancellationException
import java.io.IOException

class ResultadoLogin(
    val perfil: Perfil? = null,
    val mensaje: String = ""
)

suspend fun autenticar(
    correo: String,
    contrasena: String
): ResultadoLogin {

    SesionActual.limpiar()

    var autorizacion: String? = null
    var sesionGuardada = false

    try {
        val respuesta = RetrofitClient.api.iniciarSesion(
            InicioSesion(
                correo = correo.trim(),
                password = contrasena
            )
        )

        if (!respuesta.isSuccessful) {
            respuesta.errorBody()?.close()

            val mensaje = when (respuesta.code()) {
                401 -> "Correo o contraseña incorrectos"
                403 -> "Acceso no permitido"
                422 -> "Datos de sesión inválidos"
                429 -> "Demasiados intentos. Intenta más tarde"
                else -> "No fue posible iniciar sesión"
            }

            return ResultadoLogin(mensaje = mensaje)
        }

        val token = respuesta.body()
            ?: return ResultadoLogin(
                mensaje = "Respuesta inválida del servidor"
            )

        if (token.access_token.isNullOrBlank()) {
            return ResultadoLogin(
                mensaje = "Token de sesión inválido"
            )
        }

        autorizacion = "Bearer ${token.access_token}"

        val respuestaUsuario =
            RetrofitClient.api.obtenerSesion(autorizacion)

        if (!respuestaUsuario.isSuccessful) {
            respuestaUsuario.errorBody()?.close()

            return ResultadoLogin(
                mensaje = "No fue posible validar la sesión"
            )
        }

        val usuario = respuestaUsuario.body()
            ?: return ResultadoLogin(
                mensaje = "No se encontraron los datos del usuario"
            )

        val perfil = when (usuario.rol) {
            "presidente" -> Perfil.PRESIDENCIA
            "responsable" -> Perfil.RESPONSABLE
            "asistente" -> Perfil.ASISTENTE
            "empleado" -> Perfil.EMPLEADO
            else -> null
        }

        if (perfil == null) {
            return ResultadoLogin(
                mensaje = "Perfil de usuario no reconocido"
            )
        }

        SesionActual.guardar(token, usuario)

        if (SesionActual.obtenerToken() == null) {
            return ResultadoLogin(
                mensaje = "La sesión no es válida"
            )
        }

        sesionGuardada = true

        return ResultadoLogin(perfil = perfil)

    } catch (e: CancellationException) {
        throw e
    } catch (e: IOException) {
        return ResultadoLogin(
            mensaje = "No fue posible conectar con el servidor"
        )
    } catch (e: Exception) {
        return ResultadoLogin(
            mensaje = "Error al iniciar sesión"
        )
    } finally {
        if (!sesionGuardada) {
            SesionActual.limpiar()

            autorizacion?.let {
                try {
                    val respuesta =
                        RetrofitClient.api.cerrarSesion(it)

                    respuesta.errorBody()?.close()

                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    // No fue posible cerrar la sesión.
                }
            }
        }
    }
}

suspend fun cerrarSesionActual(): Boolean {
    val token = SesionActual.obtenerToken()
    SesionActual.limpiar()

    if (token == null) {
        return true
    }

    return try {
        val respuesta = RetrofitClient.api.cerrarSesion(token)
        respuesta.errorBody()?.close()
        respuesta.isSuccessful || respuesta.code() == 401
    } catch (e: CancellationException) {
        throw e
    } catch (_: Exception) {
        false
    }
}