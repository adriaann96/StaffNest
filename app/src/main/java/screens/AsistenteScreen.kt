package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.model.listaEstanciasPresidencia
import com.example.staffnest.navigation.RutaAsistente

@Composable
fun asistente(
    cerrarsesion: () -> Unit
) {
    val nav = rememberNavController()

    val entrada by nav.currentBackStackEntryAsState()

    val actual = entrada?.destination?.route

    // Permisos hardcodeados
    // Cambiar dinámicamente con el responsable en el próximo avance
    val verSalas = true
    val gestionarPersonal = true
    val gestionarHorarios = true
    val gestionarIncidencias = true
    val registrarAsistencia = true

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
                    .background(
                        MaterialTheme.colorScheme.primary
                    )
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

                if (gestionarPersonal) {
                    botonmenu(
                        texto = "Personal",
                        activo = actual == RutaAsistente.Personal.ruta,
                        modifier = Modifier.weight(1f)
                    ) {
                        nav.navigate(
                            RutaAsistente.Personal.ruta
                        )
                    }
                }

                if (gestionarHorarios) {
                    botonmenu(
                        texto = "Horarios",
                        activo = actual == RutaAsistente.Horarios.ruta,
                        modifier = Modifier.weight(1f)
                    ) {
                        nav.navigate(
                            RutaAsistente.Horarios.ruta
                        )
                    }
                }

                if (gestionarIncidencias) {
                    botonmenu(
                        texto = "Incidencias",
                        activo = actual == RutaAsistente.Incidencias.ruta,
                        modifier = Modifier.weight(1f)
                    ) {
                        nav.navigate(
                            RutaAsistente.Incidencias.ruta
                        )
                    }
                }
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = nav,

            startDestination = RutaAsistente.Inicio.ruta,

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            composable(
                RutaAsistente.Inicio.ruta
            ) {
                inicioasistente(
                    verSalas = verSalas,
                    gestionarPersonal = gestionarPersonal,
                    gestionarHorarios = gestionarHorarios,
                    gestionarIncidencias = gestionarIncidencias,
                    puedeRegistrarAsistencia = registrarAsistencia,

                    registrarAsistencia = {
                        nav.navigate(
                            RutaAsistente.Asistencia.ruta
                        )
                    },

                    verSala = { salaId ->
                        nav.navigate(
                            "asistente_detalle_sala/$salaId"
                        )
                    }
                )
            }

            composable(
                RutaAsistente.Personal.ruta
            ) {
                if (gestionarPersonal) {
                    personalresponsable()
                } else {
                    pantallavacia("Acceso no autorizado")
                }
            }

            composable(
                RutaAsistente.Horarios.ruta
            ) {
                if (gestionarHorarios) {
                    horariosresponsable()
                } else {
                    pantallavacia("Acceso no autorizado")
                }
            }

            composable(
                RutaAsistente.Incidencias.ruta
            ) {
                if (gestionarIncidencias) {
                    incidenciasresponsable()
                } else {
                    pantallavacia("Acceso no autorizado")
                }
            }

            composable(
                RutaAsistente.Asistencia.ruta
            ) {
                if (registrarAsistencia) {
                    asistenciaresponsable(
                        regresar = {
                            nav.popBackStack()
                        }
                    )
                } else {
                    pantallavacia("Acceso no autorizado")
                }
            }

            composable(
                RutaAsistente.DetalleSala.ruta
            ) { backStackEntry ->

                if (verSalas) {

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

                } else {
                    pantallavacia("Acceso no autorizado")
                }
            }
        }
    }
}
