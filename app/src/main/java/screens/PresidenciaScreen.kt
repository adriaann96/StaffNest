package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.model.listaEstanciasPresidencia
import com.example.staffnest.navigation.RutaPresidencia

@Composable
fun presidencia(
    cerrarsesion: () -> Unit
) {

    val nav = rememberNavController()

    val entrada =
        nav.currentBackStackEntryAsState()

    val actual =
        entrada.value?.destination?.route


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
                    texto = "Estancias",
                    activo =
                        actual == RutaPresidencia.Estancias.ruta ||
                                actual?.startsWith(
                                    RutaPresidencia.DetalleEstancia.ruta
                                ) == true ||
                                actual?.startsWith(
                                    RutaPresidencia.DetalleSala.ruta
                                ) == true,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaPresidencia.Estancias.ruta
                    )
                }


                botonmenu(
                    texto = "Estadísticas",
                    activo =
                        actual == RutaPresidencia.Estadisticas.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaPresidencia.Estadisticas.ruta
                    )
                }


                botonmenu(
                    texto = "Reportes",
                    activo =
                        actual == RutaPresidencia.Reportes.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaPresidencia.Reportes.ruta
                    )
                }


                botonmenu(
                    texto = "Personal",
                    activo =
                        actual == RutaPresidencia.Personal.ruta,
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
            startDestination =
                RutaPresidencia.Estancias.ruta,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(espacio)
        ) {


            composable(
                RutaPresidencia.Estancias.ruta
            ) {

                estanciaspresidencia(
                    abrirEstancia = { id ->

                        nav.navigate(
                            "${RutaPresidencia.DetalleEstancia.ruta}/$id"
                        )
                    }
                )
            }


            composable(
                "${RutaPresidencia.DetalleEstancia.ruta}/{id}"
            ) { backStack ->

                val id =
                    backStack.arguments
                        ?.getString("id")
                        ?.toIntOrNull()

                val estancia =
                    listaEstanciasPresidencia.find {
                        it.id == id
                    }


                if (estancia != null) {

                    detalleestancia(
                        estancia = estancia,

                        regresar = {
                            nav.popBackStack()
                        },

                        abrirSala = { salaId ->

                            nav.navigate(
                                "${RutaPresidencia.DetalleSala.ruta}/${estancia.id}/$salaId"
                            )
                        }
                    )

                } else {

                    pantallavacia(
                        "Estancia no encontrada"
                    )
                }
            }


            composable(
                "${RutaPresidencia.DetalleSala.ruta}/{estanciaId}/{salaId}"
            ) { backStack ->

                val estanciaId =
                    backStack.arguments
                        ?.getString("estanciaId")
                        ?.toIntOrNull()

                val salaId =
                    backStack.arguments
                        ?.getString("salaId")
                        ?.toIntOrNull()


                val estancia =
                    listaEstanciasPresidencia.find {
                        it.id == estanciaId
                    }


                val sala =
                    estancia?.salas?.find {
                        it.id == salaId
                    }


                if (sala != null) {

                    detallesala(
                        sala = sala,

                        regresar = {
                            nav.popBackStack()
                        }
                    )

                } else {

                    pantallavacia(
                        "Sala no encontrada"
                    )
                }
            }


            composable(
                RutaPresidencia.Estadisticas.ruta
            ) {

                estadisticaspresidencia()
            }


            composable(
                RutaPresidencia.Reportes.ruta
            ) {

                reportespresidencia()
            }


            composable(
                RutaPresidencia.Personal.ruta
            ) {

                personalpresidencia()
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
            .background(
                MaterialTheme.colorScheme.background
            ),

        contentAlignment =
            Alignment.Center
    ) {

        Text(
            text = nombre,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )
    }
}