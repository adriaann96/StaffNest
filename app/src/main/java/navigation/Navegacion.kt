package com.example.staffnest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.model.Perfil
import com.example.staffnest.screens.coordinador
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
                    when (perfil) {
                        Perfil.PRESIDENCIA -> {
                            nav.navigate(
                                Ruta.Presidencia.ruta
                            )
                        }
                        Perfil.RESPONSABLE -> {
                            nav.navigate(
                                Ruta.Responsable.ruta
                            )
                        }
                        Perfil.COORDINADOR -> {
                            nav.navigate(
                                Ruta.Coordinador.ruta
                            )
                        }
                        Perfil.EMPLEADO -> {
                            nav.navigate(
                                Ruta.Empleado.ruta
                            )
                        }
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
            Ruta.Coordinador.ruta
        ) {
            coordinador(
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
    nav.navigate(
        Ruta.Login.ruta
    ) {
        popUpTo(
            Ruta.Login.ruta
        ) {
            inclusive = true
        }
    }
}