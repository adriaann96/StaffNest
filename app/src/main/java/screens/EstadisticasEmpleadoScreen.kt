package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun estadisticasempleado(
    verHistorial: () -> Unit
) {

    var periodo by remember {
        mutableStateOf("Últimos 30 días")
    }

    var menuPeriodo by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp)
    ) {

        Text(
            text = "Mis Estadísticas",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = {
                    menuPeriodo = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.10f
                            )
                    )
            ) {

                Text(
                    text = "$periodo ▼",
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            DropdownMenu(
                expanded = menuPeriodo,
                onDismissRequest = {
                    menuPeriodo = false
                }
            ) {

                DropdownMenuItem(
                    text = {
                        Text("Hoy")
                    },
                    onClick = {
                        periodo = "Hoy"
                        menuPeriodo = false
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("Últimos 15 días")
                    },
                    onClick = {
                        periodo = "Últimos 15 días"
                        menuPeriodo = false
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("Últimos 30 días")
                    },
                    onClick = {
                        periodo = "Últimos 30 días"
                        menuPeriodo = false
                    }
                )
            }
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "Resumen de Asistencias",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            tarjetaestadisticaempleado(
                titulo = "Asistencias",
                numero = "15",
                modifier = Modifier.weight(1f)
            )

            tarjetaestadisticaempleado(
                titulo = "Inasistencias",
                numero = "1",
                modifier = Modifier.weight(1f)
            )
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            tarjetaestadisticaempleado(
                titulo = "Salida temprana",
                numero = "2",
                modifier = Modifier.weight(1f)
            )

            tarjetaestadisticaempleado(
                titulo = "Retrasos",
                numero = "3",
                modifier = Modifier.weight(1f)
            )
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Button(
            onClick = {
                verHistorial()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Ver registro detallado"
            )
        }
    }
}


@Composable
fun tarjetaestadisticaempleado(
    titulo: String,
    numero: String,
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
                text = titulo,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = numero,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}