package com.example.staffnest.screens

import androidx.compose.foundation.background
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
fun personalpresidencia() {

    var tipo by remember {
        mutableStateOf("altas")
    }

    var periodo by remember {
        mutableStateOf("Últimos 6 meses")
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
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {

        Text(
            text = "Historial de Personal",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            botonpersonal(
                texto = "Altas",
                activo = tipo == "altas",
                modifier = Modifier.weight(1f)
            ) {
                tipo = "altas"
            }


            botonpersonal(
                texto = "Bajas",
                activo = tipo == "bajas",
                modifier = Modifier.weight(1f)
            ) {
                tipo = "bajas"
            }
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Button(
            onClick = {
                menuPeriodo = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
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
                    Text("Último mes")
                },
                onClick = {
                    periodo = "Último mes"
                    menuPeriodo = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Últimos 3 meses")
                },
                onClick = {
                    periodo = "Últimos 3 meses"
                    menuPeriodo = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Últimos 6 meses")
                },
                onClick = {
                    periodo = "Últimos 6 meses"
                    menuPeriodo = false
                }
            )
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        if (tipo == "altas") {

            tarjetaMovimientoPersonal(
                nombre = "Ana López",
                puesto = "Asistente educativa",
                fecha = "Alta: 10/11/2026",
                detalle = "Estancia 1"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            tarjetaMovimientoPersonal(
                nombre = "María García",
                puesto = "Maestra",
                fecha = "Alta: 28/10/2026",
                detalle = "Estancia 2"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            tarjetaMovimientoPersonal(
                nombre = "Sofía Martínez",
                puesto = "Asistente educativa",
                fecha = "Alta: 05/10/2026",
                detalle = "Estancia 3"
            )

        } else {

            tarjetaMovimientoPersonal(
                nombre = "María López",
                puesto = "Asistente educativa",
                fecha = "Baja: 12/09/2026",
                detalle = "Motivo: Renuncia"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            tarjetaMovimientoPersonal(
                nombre = "Carlos Medina",
                puesto = "Auxiliar de limpieza",
                fecha = "Baja: 28/08/2026",
                detalle = "Motivo: Término de contrato"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            tarjetaMovimientoPersonal(
                nombre = "Ana Torres",
                puesto = "Asistente educativa",
                fecha = "Baja: 15/08/2026",
                detalle = "Motivo: Motivos personales"
            )
        }


        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}


@Composable
fun botonpersonal(
    texto: String,
    activo: Boolean,
    modifier: Modifier = Modifier,
    accion: () -> Unit
) {

    Button(
        onClick = {
            accion()
        },

        modifier = modifier,

        shape =
            RoundedCornerShape(18.dp),

        colors =
            ButtonDefaults.buttonColors(
                containerColor =
                    if (activo) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.15f
                        )
                    }
            )
    ) {

        Text(
            text = texto,
            color =
                MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun tarjetaMovimientoPersonal(
    nombre: String,
    puesto: String,
    fecha: String,
    detalle: String
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

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
                text = nombre,
                fontSize = 18.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text = puesto,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text = fecha,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text = detalle,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}