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
import com.example.staffnest.navigation.RutaCoordinador
import androidx.compose.material3.MaterialTheme
@Composable
fun coordinador(
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
                    activo = actual == RutaCoordinador.Inicio.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaCoordinador.Inicio.ruta
                    )
                }
                botonmenu(
                    texto = "Estancias",
                    activo = actual == RutaCoordinador.Estancias.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaCoordinador.Estancias.ruta
                    )
                }
                botonmenu(
                    texto = "Reportes",
                    activo = actual == RutaCoordinador.Reportes.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaCoordinador.Reportes.ruta
                    )
                }
                botonmenu(
                    texto = "Perfil",
                    activo = actual == RutaCoordinador.Perfil.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaCoordinador.Perfil.ruta
                    )
                }
            }
        }

    ) { espacio ->
        NavHost(
            navController = nav,
            startDestination = RutaCoordinador.Inicio.ruta,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {
            composable(
                RutaCoordinador.Inicio.ruta
            ) {
                pantallavacia("Inicio Coordinador")
            }
            composable(
                RutaCoordinador.Estancias.ruta
            ) {
                pantallavacia("Estancias")
            }
            composable(
                RutaCoordinador.Reportes.ruta
            ) {
                pantallavacia("Reportes")
            }
            composable(
                RutaCoordinador.Perfil.ruta
            ) {
                pantallavacia("Perfil")
            }
        }
    }
}