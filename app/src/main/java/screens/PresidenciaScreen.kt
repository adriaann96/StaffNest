package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.navigation.RutaPresidencia
import androidx.compose.material3.MaterialTheme
@Composable
fun presidencia(
    cerrarsesion: () -> Unit
) {
    val nav = rememberNavController()
    val entrada = nav.currentBackStackEntryAsState()
    val actual = entrada.value?.destination?.route

    Scaffold(
        topBar = {
            barrasuperior(cerrarsesion = cerrarsesion)
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                botonmenu(
                    texto = "Estancias",
                    activo = actual == RutaPresidencia.Estancias.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaPresidencia.Estancias.ruta
                    )
                }
                botonmenu(
                    texto = "Estadísticas",
                    activo = actual == RutaPresidencia.Estadisticas.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaPresidencia.Estadisticas.ruta
                    )
                }
                botonmenu(
                    texto = "Reportes",
                    activo = actual == RutaPresidencia.Reportes.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaPresidencia.Reportes.ruta
                    )
                }
                botonmenu(
                    texto = "Personal",
                    activo = actual == RutaPresidencia.Personal.ruta,
                    modifier = Modifier.weight(1f)
                ) {
                    nav.navigate(
                        RutaPresidencia.Personal.ruta
                    )
                }
            }
        }

    ) { espacio ->

        NavHost(
            navController = nav,
            startDestination = RutaPresidencia.Estancias.ruta,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {
            composable(
                RutaPresidencia.Estancias.ruta
            ) {
                pantallavacia("Estancias")
            }
            composable(
                RutaPresidencia.Estadisticas.ruta
            ) {
                pantallavacia("Estadísticas")
            }
            composable(
                RutaPresidencia.Reportes.ruta
            ) {
                pantallavacia("Reportes")
            }
            composable(
                RutaPresidencia.Personal.ruta
            ) {
                pantallavacia("Personal")
            }
        }
    }
}

@Composable
fun pantallavacia(
    nombre: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = nombre,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}