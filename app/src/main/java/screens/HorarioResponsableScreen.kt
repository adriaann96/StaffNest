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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TurnoResponsable(
    val nombre: String,
    val horaInicio: String,
    val horaFin: String,
    val toleranciaInicio: Int,
    val toleranciaFin: Int
)

data class HorarioPersonalResponsable(
    val id: Int,
    val nombre: String,
    val sala: String,
    val turno: String
)

@Composable
fun horariosresponsable() {

    val turnos =
        listOf(

            TurnoResponsable(
                nombre = "Matutino",
                horaInicio = "08:00",
                horaFin = "16:00",
                toleranciaInicio = 10,
                toleranciaFin = 10
            ),

            TurnoResponsable(
                nombre = "Vespertino",
                horaInicio = "14:00",
                horaFin = "20:00",
                toleranciaInicio = 10,
                toleranciaFin = 10
            )
        )


    val personal =
        remember {

            mutableStateListOf(

                HorarioPersonalResponsable(
                    id = 1,
                    nombre = "Ana López",
                    sala = "Sala A",
                    turno = "Matutino"
                ),

                HorarioPersonalResponsable(
                    id = 2,
                    nombre = "María García",
                    sala = "Sala A",
                    turno = "Matutino"
                ),

                HorarioPersonalResponsable(
                    id = 3,
                    nombre = "Carlos Medina",
                    sala = "Sala B",
                    turno = "Matutino"
                ),

                HorarioPersonalResponsable(
                    id = 4,
                    nombre = "Andrea Torres",
                    sala = "Sala B",
                    turno = "Matutino"
                ),

                HorarioPersonalResponsable(
                    id = 5,
                    nombre = "Sofía Martínez",
                    sala = "Sala C",
                    turno = "Matutino"
                )
            )
        }


    var empleadoSeleccionado by remember {
        mutableStateOf<HorarioPersonalResponsable?>(null)
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
            text = "Horarios",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier =
                Modifier.height(5.dp)
        )


        Text(
            text = "Estancia 2",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier =
                Modifier.height(25.dp)
        )


        Text(
            text = "Turnos disponibles",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        turnos.forEach { turno ->

            tarjetaTurnoResponsable(
                turno = turno
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }


        Spacer(
            modifier =
                Modifier.height(25.dp)
        )


        Text(
            text = "Personal",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        personal.forEach { empleado ->

            val turno =
                turnos.firstOrNull {

                    it.nombre ==
                            empleado.turno
                }


            tarjetaHorarioPersonal(
                empleado = empleado,

                horario =
                    if (turno != null) {

                        "${turno.horaInicio} - ${turno.horaFin}"

                    } else {

                        "Sin horario"
                    },

                accion = {

                    empleadoSeleccionado =
                        empleado
                }
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )
        }


        if (
            empleadoSeleccionado != null
        ) {

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )


            formularioAsignarTurno(
                empleado =
                    empleadoSeleccionado!!,

                turnos =
                    turnos,

                guardar = { nuevoTurno ->

                    val posicion =
                        personal.indexOfFirst {

                            it.id ==
                                    empleadoSeleccionado!!.id
                        }


                    if (
                        posicion != -1
                    ) {

                        personal[posicion] =
                            personal[posicion].copy(
                                turno =
                                    nuevoTurno
                            )
                    }


                    empleadoSeleccionado =
                        null
                },

                cancelar = {

                    empleadoSeleccionado =
                        null
                }
            )
        }


        Spacer(
            modifier =
                Modifier.height(100.dp)
        )
    }
}


@Composable
fun tarjetaTurnoResponsable(
    turno: TurnoResponsable
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

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

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                text =
                    turno.nombre,

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
                text =
                    "${turno.horaInicio} - ${turno.horaFin}",

                fontSize = 15.sp,

                color =
                    MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text =
                    "Tolerancia entrada: ${turno.toleranciaInicio} min",
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )

            Text(
                text =
                    "Tolerancia salida: ${turno.toleranciaFin} min",
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun tarjetaHorarioPersonal(
    empleado: HorarioPersonalResponsable,
    horario: String,
    accion: () -> Unit
) {

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
                    text =
                        empleado.nombre,

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                Text(
                    text =
                        empleado.sala,

                    fontSize = 14.sp,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text =
                        empleado.turno,

                    fontSize = 14.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )


                Text(
                    text =
                        horario,

                    fontSize = 13.sp,

                    color =
                        MaterialTheme.colorScheme.onBackground
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
fun formularioAsignarTurno(
    empleado: HorarioPersonalResponsable,
    turnos: List<TurnoResponsable>,
    guardar: (String) -> Unit,
    cancelar: () -> Unit
) {

    var turnoSeleccionado by remember(
        empleado
    ) {

        mutableStateOf(
            empleado.turno
        )
    }


    var mostrarMenu by remember {

        mutableStateOf(false)
    }


    val turnoActual =
        turnos.firstOrNull {

            it.nombre ==
                    turnoSeleccionado
        }


    Card(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.08f
                    )
            )
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                text =
                    "Asignar turno",

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            Text(
                text =
                    empleado.nombre,

                fontSize = 18.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    empleado.sala,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            Text(
                text =
                    "Turno",

                fontSize = 14.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )


            Button(
                onClick = {

                    mostrarMenu =
                        true
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "$turnoSeleccionado ▼"
                )
            }


            DropdownMenu(
                expanded =
                    mostrarMenu,

                onDismissRequest = {

                    mostrarMenu =
                        false
                }
            ) {

                turnos.forEach { turno ->

                    DropdownMenuItem(
                        text = {

                            Text(
                                turno.nombre
                            )
                        },

                        onClick = {

                            turnoSeleccionado =
                                turno.nombre

                            mostrarMenu =
                                false
                        }
                    )
                }
            }


            if (
                turnoActual != null
            ) {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                Text(
                    text =
                        "Horario: ${turnoActual.horaInicio} - ${turnoActual.horaFin}",
                    fontSize = 15.sp,
                    color =
                        MaterialTheme.colorScheme.primary
                )
                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text =
                        "Tolerancia de entrada: ${turnoActual.toleranciaInicio} min",
                    fontSize = 14.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text =
                        "Tolerancia de salida: ${turnoActual.toleranciaFin} min",
                    fontSize = 14.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            Button(
                onClick = {

                    guardar(
                        turnoSeleccionado
                    )
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "Guardar turno"
                )
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Button(
                onClick = {

                    cancelar()
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Cancelar"
                )
            }
        }
    }
}