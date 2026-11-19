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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.listaEstanciasPresidencia
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

@Composable
fun estadisticaspresidencia() {

    var seccion by remember {
        mutableStateOf("personal")
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
            text = "Estadísticas Generales",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {


            botontipoestadistica(
                texto = "Personal",
                activo = seccion == "personal",
                modifier = Modifier.weight(1f)
            ) {

                seccion = "personal"
            }


            botontipoestadistica(
                texto = "Asistencias",
                activo = seccion == "asistencias",
                modifier = Modifier.weight(1f)
            ) {

                seccion = "asistencias"
            }


            botontipoestadistica(
                texto = "Incidencias",
                activo = seccion == "incidencias",
                modifier = Modifier.weight(1f)
            ) {

                seccion = "incidencias"
            }
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(15.dp),

            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
        ) {

            var periodo by remember {
                mutableStateOf("Últimos 30 días")
            }

            var menuPeriodo by remember {
                mutableStateOf(false)
            }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

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
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        when (seccion) {

            "personal" -> {
                estadisticaspersonal()
            }

            "asistencias" -> {
                estadisticasasistencias()
            }

            "incidencias" -> {
                estadisticasincidencias()
            }
        }


        Spacer(
            modifier = Modifier.height(80.dp)
        )
    }
}


@Composable
fun botontipoestadistica(
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
                MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp
        )
    }
}


@Composable
fun estadisticaspersonal() {

    val totalEmpleados =
        listaEstanciasPresidencia.sumOf {
            it.empleados
        }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        tarjetaestadistica(
            titulo = "Total de empleados",
            numero = totalEmpleados.toString(),
            detalle = "Personal activo",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "Altas",
            numero = "12",
            detalle = "Últimos 30 días",
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

        tarjetaestadistica(
            titulo = "Bajas",
            numero = "4",
            detalle = "Últimos 30 días",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "En vacaciones",
            numero = "6",
            detalle = "Actualmente",
            modifier = Modifier.weight(1f)
        )
    }


    Spacer(
        modifier = Modifier.height(25.dp)
    )


    Text(
        text = "Empleados por estancia",
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        color =
            MaterialTheme.colorScheme.onBackground
    )


    Spacer(
        modifier = Modifier.height(15.dp)
    )


    listaEstanciasPresidencia.forEach { estancia ->

        barraestancia(
            nombre = estancia.nombre,
            empleados = estancia.empleados
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}


@Composable
fun estadisticasasistencias() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        tarjetaestadistica(
            titulo = "A tiempo",
            numero = "118",
            detalle = "Entradas",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "Entrada tardía",
            numero = "8",
            detalle = "Entradas",
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

        tarjetaestadistica(
            titulo = "Entrada temprana",
            numero = "10",
            detalle = "Entradas",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "Ausentes",
            numero = "6",
            detalle = "Registros",
            modifier = Modifier.weight(1f)
        )
    }


    Spacer(
        modifier = Modifier.height(25.dp)
    )


    Text(
        text = "Resumen de asistencias",
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        color =
            MaterialTheme.colorScheme.onBackground
    )


    Spacer(
        modifier = Modifier.height(15.dp)
    )


    Card(
        modifier = Modifier.fillMaxWidth(),

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
                text = "142 empleados registrados",
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
    }
}


@Composable
fun estadisticasincidencias() {

    val totalIncidencias =
        listaEstanciasPresidencia.sumOf {
            it.incidenciasHoy
        }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        tarjetaestadistica(
            titulo = "Incidencias hoy",
            numero = totalIncidencias.toString(),
            detalle = "Todas las estancias",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "En revisión",
            numero = "2",
            detalle = "Pendientes",
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

        tarjetaestadistica(
            titulo = "Resueltas",
            numero = "8",
            detalle = "Últimos 30 días",
            modifier = Modifier.weight(1f)
        )


        tarjetaestadistica(
            titulo = "Descartadas",
            numero = "1",
            detalle = "Últimos 30 días",
            modifier = Modifier.weight(1f)
        )
    }


    Spacer(
        modifier = Modifier.height(25.dp)
    )


    Text(
        text = "Incidencias por estancia",
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        color =
            MaterialTheme.colorScheme.onBackground
    )


    Spacer(
        modifier = Modifier.height(15.dp)
    )


    listaEstanciasPresidencia.forEach { estancia ->

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = estancia.nombre,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text =
                        "${estancia.incidenciasHoy} incidencias",
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }
}


@Composable
fun tarjetaestadistica(
    titulo: String,
    numero: String,
    detalle: String,
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
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            Text(
                text = numero,
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text = detalle,
                fontSize = 12.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun barraestancia(
    nombre: String,
    empleados: Int
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

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = nombre,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text =
                        "$empleados empleados",
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.15f
                        ),
                        RoundedCornerShape(10.dp)
                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(
                            fraction =
                                empleados / 40f
                        )
                        .height(16.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(10.dp)
                        )
                )
            }
        }
    }
}