package com.example.staffnest.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.model.Perfil
import com.example.staffnest.network.SesionActual
import com.example.staffnest.network.cerrarSesionActual
import com.example.staffnest.screens.asistente
import com.example.staffnest.screens.empleado
import com.example.staffnest.screens.login
import com.example.staffnest.screens.presidencia
import com.example.staffnest.screens.responsable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun navegacion() {
    val nav = rememberNavController()
    val scope = rememberCoroutineScope()
    val contexto = LocalContext.current
    val usuario = SesionActual.usuario

    LaunchedEffect(usuario) {
        if (usuario != null) {
            while (SesionActual.obtenerToken() != null) {
                delay(1000)
            }
        }
    }

    val cerrar: () -> Unit = {
        scope.launch {
            val revocada = cerrarSesionActual()
            cerrarsesion(nav)

            if (!revocada) {
                Toast.makeText(
                    contexto,
                    "Sesión local cerrada. No se pudo confirmar el cierre en el servidor.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    NavHost(
        navController = nav,
        startDestination = Ruta.Login.ruta
    ) {
        composable(Ruta.Login.ruta) {
            login(
                entrar = { perfil ->
                    val destino = when (perfil) {
                        Perfil.PRESIDENCIA -> Ruta.Presidencia.ruta
                        Perfil.RESPONSABLE -> Ruta.Responsable.ruta
                        Perfil.ASISTENTE -> Ruta.Asistente.ruta
                        Perfil.EMPLEADO -> Ruta.Empleado.ruta
                    }

                    nav.navigate(destino) {
                        popUpTo(Ruta.Login.ruta) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Ruta.Presidencia.ruta) {
            validarSesion(nav, "presidente") {
                presidencia(cerrarsesion = cerrar)
            }
        }

        composable(Ruta.Responsable.ruta) {
            validarSesion(nav, "responsable") {
                responsable(cerrarsesion = cerrar)
            }
        }

        composable(Ruta.Asistente.ruta) {
            validarSesion(nav, "asistente") {
                asistente(cerrarsesion = cerrar)
            }
        }

        composable(Ruta.Empleado.ruta) {
            validarSesion(nav, "empleado") {
                empleado(cerrarsesion = cerrar)
            }
        }
    }
}

@Composable
fun validarSesion(
    nav: NavHostController,
    rol: String,
    contenido: @Composable () -> Unit
) {
    val usuario = SesionActual.usuario

    if (usuario != null && usuario.rol == rol) {
        contenido()
    } else {
        LaunchedEffect(Unit) {
            cerrarsesion(nav)
        }
    }
}

fun cerrarsesion(nav: NavHostController) {
    SesionActual.limpiar()

    if (nav.currentDestination?.route != Ruta.Login.ruta) {
        nav.navigate(Ruta.Login.ruta) {
            popUpTo(nav.graph.id) {
                inclusive = false
            }
            launchSingleTop = true
        }
    }
}