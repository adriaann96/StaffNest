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
import com.example.staffnest.navigation.RutaResponsable

@Composable
fun responsable(
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
                    activo = actual == RutaResponsable.Inicio.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaResponsable.Inicio.ruta
                    )
                }
                botonmenu(
                    texto = "Estancias",
                    activo = actual == RutaResponsable.Estancias.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaResponsable.Estancias.ruta
                    )
                }
                botonmenu(
                    texto = "Reportes",
                    activo = actual == RutaResponsable.Reportes.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaResponsable.Reportes.ruta
                    )
                }
                botonmenu(
                    texto = "Perfil",
                    activo = actual == RutaResponsable.Perfil.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaResponsable.Perfil.ruta
                    )
                }
            }
        }

    ) { espacio ->
        NavHost(
            navController = nav,
            startDestination = RutaResponsable.Inicio.ruta,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {
            composable(
                RutaResponsable.Inicio.ruta
            ) {
                pantallavacia("Inicio Responsable")
            }
            composable(
                RutaResponsable.Estancias.ruta
            ) {
                pantallavacia("Estancias")
            }
            composable(
                RutaResponsable.Reportes.ruta
            ) {
                pantallavacia("Reportes")
            }
            composable(
                RutaResponsable.Perfil.ruta
            ) {
                pantallavacia("Perfil")
            }
        }
    }
}