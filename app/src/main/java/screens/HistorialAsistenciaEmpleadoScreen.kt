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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RegistroAsistenciaEmpleado(
    val fecha: String,
    val entrada: String,
    val salida: String,
    val estadoEntrada: String,
    val horasTrabajadas: String,
    val salidaTemprana: Boolean
)


val registrosEmpleado = listOf(

    RegistroAsistenciaEmpleado(
        fecha = "Lun. 23 nov.",
        entrada = "08:57",
        salida = "16:02",
        estadoEntrada = "A tiempo",
        horasTrabajadas = "7 h 05 min",
        salidaTemprana = false
    ),

    RegistroAsistenciaEmpleado(
        fecha = "Vie. 20 nov.",
        entrada = "09:08",
        salida = "16:00",
        estadoEntrada = "Entrada tardía",
        horasTrabajadas = "6 h 52 min",
        salidaTemprana = false
    ),

    RegistroAsistenciaEmpleado(
        fecha = "Jue. 19 nov.",
        entrada = "08:55",
        salida = "15:30",
        estadoEntrada = "A tiempo",
        horasTrabajadas = "6 h 35 min",
        salidaTemprana = true
    ),

    RegistroAsistenciaEmpleado(
        fecha = "Mié. 18 nov.",
        entrada = "08:59",
        salida = "16:05",
        estadoEntrada = "A tiempo",
        horasTrabajadas = "7 h 06 min",
        salidaTemprana = false
    )
)


@Composable
fun historialasistenciaempleado(
    regresar: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp)
    ) {

        item {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "←",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable {
                            regresar()
                        }
                        .padding(8.dp)
                )


                Text(
                    text = "Historial de Asistencia",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }


        items(
            registrosEmpleado
        ) { registro ->

            tarjetaRegistroEmpleado(
                registro = registro
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }


        item {

            Spacer(
                modifier = Modifier.height(60.dp)
            )
        }
    }
}


@Composable
fun tarjetaRegistroEmpleado(
    registro: RegistroAsistenciaEmpleado
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = registro.fecha,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text = registro.horasTrabajadas,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text =
                    "Entrada: ${registro.entrada}",
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    "Estado: ${registro.estadoEntrada}",
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text =
                    "Salida: ${registro.salida}",
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            if (registro.salidaTemprana) {

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Salida temprana",
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}