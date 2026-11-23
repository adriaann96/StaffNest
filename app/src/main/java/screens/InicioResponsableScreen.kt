package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.listaEstanciasPresidencia


@Composable
fun inicioresponsable(
    registrarAsistencia: () -> Unit,
    verSala: (Int) -> Unit,
    verSolicitudes: () -> Unit
) {

    // Por ahora simulamos que la responsable
    // pertenece a la Estancia 2.
    // Después vendrá del usuario que inició sesión.
    val estancia =
        listaEstanciasPresidencia.first {
            it.id == 2
        }


    // Dato temporal.
    // Después se obtiene de las asistencias del día.
    val empleadosPresentes = 26


    // Dato temporal.
    // Después se obtiene de las solicitudes.
    val solicitudesPendientes = 3


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {

        Text(
            text = "Mi estancia",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(5.dp)
        )


        Text(
            text = estancia.nombre,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "Resumen de mi estancia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            tarjetaresumenresponsable(
                numero =
                    estancia.empleados.toString(),
                texto =
                    "Empleados\nprogramados",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            )


            tarjetaresumenresponsable(
                numero =
                    empleadosPresentes.toString(),
                texto =
                    "Presentes\nhoy",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp)
            )
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            tarjetaresumenresponsable(
                numero =
                    estancia.incidenciasHoy.toString(),
                texto =
                    "Incidencias\nhoy",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            )


            tarjetaresumenresponsable(
                numero =
                    estancia.salasConAlerta.toString(),
                texto =
                    "Salas con\nalerta",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp)
            )
        }


        // Registrar asistencia se queda porque
        // no existe como opción en la barra inferior.

        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Button(
            onClick = {
                registrarAsistencia()
            },

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(18.dp)
        ) {

            Text(
                text = "Registrar asistencia",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Text(
            text = "Estado de salas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        estancia.salas.forEach { sala ->

            tarjetasalaresponsable(
                nombre =
                    sala.nombre,

                actual =
                    sala.personalActual,

                requerido =
                    sala.personalRequerido,

                accion = {

                    verSala(
                        sala.id
                    )
                }
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        tarjetasolicitudesresponsable(
            cantidad =
                solicitudesPendientes,

            accion = {
                verSolicitudes()
            }
        )


        Spacer(
            modifier = Modifier.height(100.dp)
        )
    }
}


@Composable
fun tarjetaresumenresponsable(
    numero: String,
    texto: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                text = numero,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.primary
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text = texto,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun tarjetasalaresponsable(
    nombre: String,
    actual: Int,
    requerido: Int,
    accion: () -> Unit
) {

    val completa =
        actual >= requerido


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                accion()
            },

        shape =
            RoundedCornerShape(18.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.08f
                    )
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = nombre,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text =
                        "$actual/$requerido personal",

                    fontSize = 14.sp,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(3.dp)
                )


                Text(
                    text =
                        if (completa) {
                            "Personal completo"
                        } else {
                            "Personal insuficiente"
                        },

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        if (completa) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                )
            }


            Text(
                text = "›",
                fontSize = 28.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun tarjetasolicitudesresponsable(
    cantidad: Int,
    accion: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                accion()
            },

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        "Solicitudes de empleados",

                    fontSize = 17.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(5.dp)
                )


                Text(
                    text =
                        "$cantidad pendientes",

                    fontSize = 14.sp,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(3.dp)
                )


                Text(
                    text = "Solo consulta",
                    fontSize = 12.sp,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Text(
                text = "›",
                fontSize = 28.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}