package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.navigation.RutaAsistente
import androidx.compose.material3.MaterialTheme
@Composable
fun asistente(
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
                    texto = "Inicio",
                    activo = actual == RutaAsistente.Inicio.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaAsistente.Inicio.ruta
                    )
                }
                botonmenu(
                    texto = "Estancias",
                    activo = actual == RutaAsistente.Estancias.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaAsistente.Estancias.ruta
                    )
                }
                botonmenu(
                    texto = "Reportes",
                    activo = actual == RutaAsistente.Reportes.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaAsistente.Reportes.ruta
                    )
                }
                botonmenu(
                    texto = "Perfil",
                    activo = actual == RutaAsistente.Perfil.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaAsistente.Perfil.ruta
                    )
                }
            }
        }

    ) { espacio ->
        NavHost(
            navController = nav,
            startDestination = RutaAsistente.Inicio.ruta,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {
            composable(
                RutaAsistente.Inicio.ruta
            ) {
                pantallavacia("Inicio Asistente")
            }
            composable(
                RutaAsistente.Estancias.ruta
            ) {
                pantallavacia("Estancias")
            }
            composable(
                RutaAsistente.Reportes.ruta
            ) {
                pantallavacia("Reportes")
            }
            composable(
                RutaAsistente.Perfil.ruta
            ) {
                pantallavacia("Perfil")
            }
        }
    }
}