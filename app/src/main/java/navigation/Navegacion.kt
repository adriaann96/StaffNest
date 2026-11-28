package com.example.staffnest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.model.Perfil
import com.example.staffnest.network.SesionActual
import com.example.staffnest.screens.asistente
import com.example.staffnest.screens.empleado
import com.example.staffnest.screens.login
import com.example.staffnest.screens.presidencia
import com.example.staffnest.screens.responsable

@Composable
fun navegacion() {
    val nav = rememberNavController()
    NavHost(
        navController = nav,
        startDestination = Ruta.Login.ruta
    ) {
        composable(
            Ruta.Login.ruta
        ) {

            login(
                entrar = { perfil ->
                    val destino = when (perfil) {
                        Perfil.PRESIDENCIA ->
                            Ruta.Presidencia.ruta

                        Perfil.RESPONSABLE ->
                            Ruta.Responsable.ruta

                        Perfil.ASISTENTE ->
                            Ruta.Asistente.ruta

                        Perfil.EMPLEADO ->
                            Ruta.Empleado.ruta
                    }

                    nav.navigate(destino) {
                        popUpTo(Ruta.Login.ruta) {
                            inclusive = true //checar pq
                        }

                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            Ruta.Presidencia.ruta
        ) {
            presidencia(
                cerrarsesion = {
                    cerrarsesion(nav)
                }
            )
        }
        composable(
            Ruta.Responsable.ruta
        ) {
            responsable(
                cerrarsesion = {
                    cerrarsesion(nav)
                }
            )
        }
        composable(
            Ruta.Asistente.ruta
        ) {
            asistente(
                cerrarsesion = {
                    cerrarsesion(nav)
                }
            )
        }
        composable(
            Ruta.Empleado.ruta
        ) {
            empleado(
                cerrarsesion = {
                    cerrarsesion(nav)
                }
            )
        }
    }
}

fun cerrarsesion(
    nav: NavHostController
) {
    SesionActual.limpiar()

    nav.navigate(Ruta.Login.ruta) {
        popUpTo(nav.graph.id) {
            inclusive = false
        }

        launchSingleTop = true
    }
}