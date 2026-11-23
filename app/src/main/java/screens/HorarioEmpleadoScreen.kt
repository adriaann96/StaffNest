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

@Composable
fun horarioempleado() {

    val estadoHoy = "normal"

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


        if (estadoHoy == "normal") {

            turnoactual()

        } else {

            estadoespecial(
                estado = estadoHoy
            )
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "Horario de esta semana",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        diashorario(
            dia = "Lunes",
            horario = "08:00 - 16:00",
            sala = "Sala A"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        diashorario(
            dia = "Martes",
            horario = "08:00 - 16:00",
            sala = "Sala A"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        diashorario(
            dia = "Miércoles",
            horario = "08:00 - 16:00",
            sala = "Sala A"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        diashorario(
            dia = "Jueves",
            horario = "08:00 - 16:00",
            sala = "Sala A"
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        diashorario(
            dia = "Viernes",
            horario = "08:00 - 16:00",
            sala = "Sala A"
        )


        Spacer(
            modifier = Modifier.height(30.dp)
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
fun turnoactual() {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(
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
                text = "Turno: Matutino",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = "Horario: 08:00 - 16:00",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = "Sala: Sala A",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = "Estancia: Estancia 2",
                fontSize = 17.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun estadoespecial(
    estado: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(
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
                text = "Estado de hoy",
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
                    when (estado) {

                        "vacaciones" -> {
                            "Vacaciones"
                        }

                        "permiso" -> {
                            "Permiso"
                        }

                        "ausencia" -> {
                            "Ausencia"
                        }

                        else -> {
                            estado
                        }
                    },

                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun diashorario(
    dia: String,
    horario: String,
    sala: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

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
                .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = dia,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = horario,
                    fontSize = 14.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Text(
                text = sala,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
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