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
import androidx.compose.material3.OutlinedTextField
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


data class IncidenciaResponsable(
    val id: Int,
    val empleado: String,
    val sala: String,
    val tipo: String,
    val titulo: String,
    val descripcion: String,
    val estado: String,
    val fecha: String,
    val resolucion: String
)


@Composable
fun incidenciasresponsable() {

    val incidencias = remember {

        mutableStateListOf(

            IncidenciaResponsable(
                id = 1,
                empleado = "",
                sala = "Sala A",
                tipo = "Material faltante",
                titulo = "Material faltante",
                descripcion =
                    "Se detectó material faltante dentro de la sala.",
                estado = "Abierto",
                fecha = "23/11/2026",
                resolucion = ""
            ),

            IncidenciaResponsable(
                id = 2,
                empleado = "Carlos Medina",
                sala = "Sala B",
                tipo = "Reporte de empleado",
                titulo = "Reporte de empleado",
                descripcion =
                    "Se registró una situación relacionada con el empleado.",
                estado = "Resuelto",
                fecha = "22/11/2026",
                resolucion =
                    "La responsable revisó la situación y dio seguimiento."
            )
        )
    }


    var mostrarFormulario by remember {
        mutableStateOf(false)
    }


    var incidenciaSeleccionada by remember {
        mutableStateOf<IncidenciaResponsable?>(null)
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
            text = "Incidencias",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(5.dp)
        )


        Text(
            text = "Estancia 2",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Button(
            onClick = {

                mostrarFormulario = true
                incidenciaSeleccionada = null
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Registrar incidencia"
            )
        }


        if (mostrarFormulario) {

            Spacer(
                modifier = Modifier.height(20.dp)
            )


            formularioincidenciaresponsable(
                guardar = { nuevaIncidencia ->

                    incidencias.add(
                        nuevaIncidencia.copy(
                            id =
                                incidencias.size + 1
                        )
                    )

                    mostrarFormulario =
                        false
                },

                cancelar = {

                    mostrarFormulario =
                        false
                }
            )
        }


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Text(
            text = "Incidencias registradas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        incidencias.forEach { incidencia ->

            tarjetaincidenciaresponsable(
                incidencia = incidencia,
                accion = {

                    incidenciaSeleccionada =
                        incidencia

                    mostrarFormulario =
                        false
                }
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        if (
            incidenciaSeleccionada != null
        ) {

            Spacer(
                modifier = Modifier.height(25.dp)
            )


            detalleincidenciaresponsable(
                incidencia =
                    incidenciaSeleccionada!!,

                actualizar = { nuevaIncidencia ->

                    val posicion =
                        incidencias.indexOfFirst {

                            it.id ==
                                    nuevaIncidencia.id
                        }


                    if (
                        posicion != -1
                    ) {

                        incidencias[posicion] =
                            nuevaIncidencia
                    }


                    incidenciaSeleccionada =
                        nuevaIncidencia
                },

                cerrar = {

                    incidenciaSeleccionada =
                        null
                }
            )
        }


        Spacer(
            modifier = Modifier.height(100.dp)
        )
    }
}


@Composable
fun formularioincidenciaresponsable(
    guardar: (IncidenciaResponsable) -> Unit,
    cancelar: () -> Unit
) {

    var empleado by remember {
        mutableStateOf("Sin empleado relacionado")
    }

    var sala by remember {
        mutableStateOf("Sala A")
    }

    var tipo by remember {
        mutableStateOf("Falla de equipo")
    }

    var descripcion by remember {
        mutableStateOf("")
    }

    var mensaje by remember {
        mutableStateOf("")
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
                text = "Registrar incidencia",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            selectorincidencia(
                titulo = "Empleado",
                valor = empleado,
                opciones = listOf(
                    "Sin empleado relacionado",
                    "Ana López",
                    "María García",
                    "Carlos Medina",
                    "Andrea Torres",
                    "Sofía Martínez"
                ),
                seleccionar = {

                    empleado = it
                }
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            selectorincidencia(
                titulo = "Sala",
                valor = sala,
                opciones = listOf(
                    "Sala A",
                    "Sala B",
                    "Sala C"
                ),
                seleccionar = {

                    sala = it
                }
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            selectorincidencia(
                titulo = "Tipo de incidencia",
                valor = tipo,
                opciones = listOf(
                    "Falla de equipo",
                    "Material dañado",
                    "Material faltante",
                    "Reporte de empleado",
                    "Otro"
                ),
                seleccionar = {

                    tipo = it
                }
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            OutlinedTextField(
                value = descripcion,

                onValueChange = {
                    descripcion = it
                    mensaje = ""
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Descripción")
                },

                minLines = 3,

                maxLines = 5
            )


            if (
                mensaje != ""
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Text(
                    text = mensaje,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.error
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Button(
                onClick = {
                    if (
                        tipo == "Reporte de empleado" &&
                        empleado == "Sin empleado relacionado"
                    ) {

                        mensaje =
                            "Selecciona el empleado relacionado"

                    } else if (
                        descripcion.isBlank()
                    ) {

                        mensaje =
                            "Falta ingresar la descripción"

                    } else {

                        guardar(

                            IncidenciaResponsable(
                                id = 0,

                                empleado =
                                    if (
                                        empleado ==
                                        "Sin empleado relacionado"
                                    ) {
                                        ""
                                    } else {
                                        empleado
                                    },

                                sala = sala,
                                tipo = tipo,
                                titulo = tipo,
                                descripcion = descripcion,
                                estado = "Abierto",
                                fecha = "23/11/2026",
                                resolucion = ""
                            )
                        )
                    }
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "Guardar incidencia"
                )
            }


            Spacer(
                modifier = Modifier.height(10.dp)
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


@Composable
fun tarjetaincidenciaresponsable(
    incidencia: IncidenciaResponsable,
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

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        incidencia.tipo,

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Bold,

                    modifier =
                        Modifier.weight(1f),

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text =
                        incidencia.estado,

                    fontSize = 13.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            if (incidencia.empleado.isNotBlank()) {

                Text(
                    text = incidencia.empleado,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Text(
                text =
                    incidencia.sala,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    incidencia.fecha,

                fontSize = 13.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )
        }
    }
}


@Composable
fun detalleincidenciaresponsable(
    incidencia: IncidenciaResponsable,
    actualizar: (IncidenciaResponsable) -> Unit,
    cerrar: () -> Unit
) {

    var resolucion by remember(incidencia) {

        mutableStateOf(
            incidencia.resolucion
        )
    }


    var mostrarResolucion by remember {

        mutableStateOf(false)
    }


    var mensaje by remember {

        mutableStateOf("")
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
                    "Detalle de incidencia",

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            if (incidencia.empleado.isNotBlank()) {

                Text(
                    text =
                        "Empleado: ${incidencia.empleado}",
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Text(
                text =
                    "Sala: ${incidencia.sala}",

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    "Tipo: ${incidencia.tipo}",

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    "Fecha: ${incidencia.fecha}",

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            Text(
                text =
                    incidencia.descripcion,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Text(
                text =
                    "Estado: ${incidencia.estado}",

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.primary
            )


            if (
                incidencia.resolucion != ""
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Text(
                    text = "Resolución",

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text =
                        incidencia.resolucion,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            if (
                incidencia.estado ==
                "Abierto"
            ) {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Button(
                    onClick = {

                        actualizar(
                            incidencia.copy(
                                estado =
                                    "En revisión"
                            )
                        )
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Marcar en revisión"
                    )
                }
            }


            if (
                incidencia.estado !=
                "Resuelto"
                &&
                incidencia.estado !=
                "Descartado"
            ) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Button(
                    onClick = {

                        mostrarResolucion =
                            true
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Resolver incidencia"
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Button(
                    onClick = {

                        actualizar(
                            incidencia.copy(
                                estado =
                                    "Descartado",
                                resolucion =
                                    "Incidencia descartada."
                            )
                        )
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Descartar incidencia"
                    )
                }
            }


            if (
                mostrarResolucion
            ) {

                Spacer(
                    modifier = Modifier.height(15.dp)
                )


                OutlinedTextField(
                    value =
                        resolucion,

                    onValueChange = {

                        resolucion = it
                        mensaje = ""
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    label = {
                        Text(
                            "Resolución"
                        )
                    },

                    minLines = 3,

                    maxLines = 5
                )


                if (
                    mensaje != ""
                ) {

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )


                    Text(
                        text = mensaje,

                        fontWeight =
                            FontWeight.Bold,

                        color =
                            MaterialTheme.colorScheme.error
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Button(
                    onClick = {

                        if (
                            resolucion.isBlank()
                        ) {

                            mensaje =
                                "Ingresa la resolución"

                        } else {

                            actualizar(
                                incidencia.copy(
                                    estado =
                                        "Resuelto",
                                    resolucion =
                                        resolucion
                                )
                            )

                            mostrarResolucion =
                                false
                        }
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Confirmar resolución"
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            Button(
                onClick = {

                    cerrar()
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Cerrar"
                )
            }
        }
    }
}


@Composable
fun selectorincidencia(
    titulo: String,
    valor: String,
    opciones: List<String>,
    seleccionar: (String) -> Unit
) {

    var mostrarMenu by remember {

        mutableStateOf(false)
    }


    Column {

        Text(
            text = titulo,

            fontSize = 14.sp,

            fontWeight =
                FontWeight.Bold,

            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(5.dp)
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
                    "$valor ▼"
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

            opciones.forEach { opcion ->

                DropdownMenuItem(
                    text = {

                        Text(
                            opcion
                        )
                    },

                    onClick = {

                        seleccionar(
                            opcion
                        )

                        mostrarMenu =
                            false
                    }
                )
            }
        }
    }
}