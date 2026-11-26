package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HorarioEmpleado(
    val nombreTurno: String,
    val inicioProgramado: String,
    val finProgramado: String,
    val toleranciaInicio: Int,
    val toleranciaFin: Int
)
@Composable
fun horarioempleado() {

    val horario = HorarioEmpleado(
        nombreTurno = "Matutino",
        inicioProgramado = "08:00",
        finProgramado = "16:00",
        toleranciaInicio = 10,
        toleranciaFin = 10
    )

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
            text = "Mi horario",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        turnoactual(
            horario = horario
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "Actividades de hoy",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        actividadempleado(
            nombre = "Actividad 1",
            descripcion = "Preparación de sala",
            horario = "09:00 - 10:30"
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        actividadempleado(
            nombre = "Actividad 2",
            descripcion = "Apoyo en actividades",
            horario = "11:00 - 12:00"
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        actividadempleado(
            nombre = "Actividad 3",
            descripcion = "Organización de materiales",
            horario = "13:00 - 14:00"
        )


        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}


@Composable
fun turnoactual(
    horario: HorarioEmpleado
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.10f
                )
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Turno de hoy",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Text(
                text =
                    "Turno: ${horario.nombreTurno}",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text =
                    "Horario: ${horario.inicioProgramado} - ${horario.finProgramado}",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text =
                    "Tolerancia de entrada: ${horario.toleranciaInicio} min",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text =
                    "Tolerancia de salida: ${horario.toleranciaFin} min",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun actividadempleado(
    nombre: String,
    descripcion: String,
    horario: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(18.dp),

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
                .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = nombre,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                text = horario,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}