package com.example.staffnest.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.botonmenu
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.navigation.RutaResponsable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import com.example.staffnest.model.listaEstanciasPresidencia
@Composable
fun responsable(
    cerrarsesion: () -> Unit
) {
    val nav =
        rememberNavController()

    val backStackEntry by
    nav.currentBackStackEntryAsState()

    val entrada by nav.currentBackStackEntryAsState()

    val actual =
        entrada?.destination?.route

    Scaffold(

        topBar = {

            barrasuperior(
                cerrarsesion =
                    cerrarsesion
            )
        },

        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primary
                    )
            ) {

                botonmenu(
                    texto = "Inicio",
                    activo =
                        actual ==
                                RutaResponsable.Inicio.ruta,
                    modifier =
                        Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaResponsable.Inicio.ruta
                    )
                }


                botonmenu(
                    texto = "Personal",
                    activo =
                        actual ==
                                RutaResponsable.Personal.ruta,
                    modifier =
                        Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaResponsable.Personal.ruta
                    )
                }


                botonmenu(
                    texto = "Horarios",
                    activo =
                        actual ==
                                RutaResponsable.Horarios.ruta,
                    modifier =
                        Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaResponsable.Horarios.ruta
                    )
                }


                botonmenu(
                    texto = "Incidencias",
                    activo =
                        actual ==
                                RutaResponsable.Incidencias.ruta,
                    modifier =
                        Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaResponsable.Incidencias.ruta
                    )
                }
            }
        }

    ) { paddingValues ->


        NavHost(
            navController = nav,

            startDestination =
                RutaResponsable.Inicio.ruta,

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {


            composable(
                RutaResponsable.Inicio.ruta
            ) {
                inicioresponsable(
                    registrarAsistencia = {
                        nav.navigate(
                            RutaResponsable.Asistencia.ruta
                        )
                    },
                    verSala = { salaId ->
                        nav.navigate(
                            "responsable_detalle_sala/$salaId"
                        )
                    },
                    verSolicitudes = {
                    }
                )
            }
            composable(
                RutaResponsable.DetalleSala.ruta
            ) { backStackEntry ->
                val salaId =
                    backStackEntry
                        .arguments
                        ?.getString("salaId")
                        ?.toIntOrNull()
                val estancia =
                    listaEstanciasPresidencia.first {
                        it.id == 2
                    }
                val sala =
                    estancia.salas.firstOrNull {
                        it.id == salaId
                    }
                if (sala != null) {
                    detallesala(
                        sala = sala,
                        regresar = {
                            nav.popBackStack()
                        }
                    )
                }
            }

            composable(
                RutaResponsable.Personal.ruta
            ) {
                personalresponsable()
            }

            composable(
                RutaResponsable.Horarios.ruta
            ) {
                horariosresponsable()
            }

            composable(
                RutaResponsable.Incidencias.ruta
            ) {
                incidenciasresponsable()
            }

            composable(
                RutaResponsable.Asistencia.ruta
            ) {
                asistenciaresponsable(
                    regresar = {
                        nav.popBackStack()
                    }
                )
            }
        }
    }
}