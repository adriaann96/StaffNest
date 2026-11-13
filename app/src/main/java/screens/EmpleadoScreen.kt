package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.navigation.RutaEmpleado

@Composable
fun empleado(
    cerrarsesion: () -> Unit
) {
    val nav = rememberNavController()
    val entrada = nav.currentBackStackEntryAsState()
    val actual = entrada.value?.destination?.route

    Scaffold(
        topBar = {
            barrasuperior(
                cerrarsesion = cerrarsesion
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                botonmenu(
                    texto = "Registro",
                    activo = actual == RutaEmpleado.Registro.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaEmpleado.Registro.ruta
                    )
                }
                botonmenu(
                    texto = "Mi horario",
                    activo = actual == RutaEmpleado.Horario.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaEmpleado.Horario.ruta
                    )
                }
                botonmenu(
                    texto = "Estadísticas",
                    activo = actual == RutaEmpleado.Estadisticas.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaEmpleado.Estadisticas.ruta
                    )
                }
            }
        }

    ) { espacio ->
        NavHost(
            navController = nav,
            startDestination = RutaEmpleado.Registro.ruta,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {
            composable(
                RutaEmpleado.Registro.ruta
            ) {
                pantallavacia("Registro")
            }
            composable(
                RutaEmpleado.Horario.ruta
            ) {
                pantallavacia("Mi horario")
            }
            composable(
                RutaEmpleado.Estadisticas.ruta
            ) {
                pantallavacia("Estadísticas")
            }
        }
    }
}