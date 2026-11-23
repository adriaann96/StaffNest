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


data class PersonalResponsable(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val tipoPersonal: String,
    val sala: String,
    val estatus: String
)


@Composable
fun personalresponsable() {

    val personal = remember {

        mutableStateListOf(

            PersonalResponsable(
                id = 1,
                nombre = "Ana",
                apellido = "López",
                correo = "ana.lopez@staffnest.com",
                tipoPersonal = "Asistente educativa",
                sala = "Sala A",
                estatus = "Activo"
            ),

            PersonalResponsable(
                id = 2,
                nombre = "María",
                apellido = "García",
                correo = "maria.garcia@staffnest.com",
                tipoPersonal = "Maestra",
                sala = "Sala A",
                estatus = "Activo"
            ),

            PersonalResponsable(
                id = 3,
                nombre = "Carlos",
                apellido = "Medina",
                correo = "carlos.medina@staffnest.com",
                tipoPersonal = "Auxiliar",
                sala = "Sala B",
                estatus = "Activo"
            ),

            PersonalResponsable(
                id = 4,
                nombre = "Andrea",
                apellido = "Torres",
                correo = "andrea.torres@staffnest.com",
                tipoPersonal = "Asistente educativa",
                sala = "Sala B",
                estatus = "Activo"
            ),

            PersonalResponsable(
                id = 5,
                nombre = "Sofía",
                apellido = "Martínez",
                correo = "sofia.martinez@staffnest.com",
                tipoPersonal = "Maestra",
                sala = "Sala C",
                estatus = "Activo"
            )
        )
    }


    var mostrarFormulario by remember {
        mutableStateOf(false)
    }


    var empleadoSeleccionado by remember {
        mutableStateOf<PersonalResponsable?>(null)
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
            text = "Personal",
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

                empleadoSeleccionado = null
                mostrarFormulario = true
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Agregar personal"
            )
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Text(
            text = "Personal de mi estancia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        personal.forEach { empleado ->

            tarjetapersonalresponsable(
                empleado = empleado,
                accion = {

                    empleadoSeleccionado =
                        empleado

                    mostrarFormulario =
                        true
                }
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        if (mostrarFormulario) {

            Spacer(
                modifier = Modifier.height(25.dp)
            )


            formulariopersonalresponsable(
                empleado =
                    empleadoSeleccionado,

                correosExistentes =
                    personal.map {
                        it.correo
                    },

                guardar = { nuevoEmpleado ->

                    if (
                        empleadoSeleccionado == null
                    ) {

                        personal.add(
                            nuevoEmpleado.copy(
                                id =
                                    personal.size + 1
                            )
                        )

                    } else {

                        val posicion =
                            personal.indexOfFirst {

                                it.id ==
                                        empleadoSeleccionado?.id
                            }


                        if (
                            posicion != -1
                        ) {

                            personal[posicion] =
                                nuevoEmpleado.copy(
                                    id =
                                        empleadoSeleccionado!!.id
                                )
                        }
                    }


                    mostrarFormulario =
                        false

                    empleadoSeleccionado =
                        null
                },

                darDeBaja = {

                    val empleado =
                        empleadoSeleccionado

                    if (
                        empleado != null
                    ) {

                        val posicion =
                            personal.indexOfFirst {

                                it.id ==
                                        empleado.id
                            }


                        if (
                            posicion != -1
                        ) {

                            personal[posicion] =
                                empleado.copy(
                                    estatus = "Baja"
                                )
                        }
                    }


                    mostrarFormulario =
                        false

                    empleadoSeleccionado =
                        null
                },

                cancelar = {

                    mostrarFormulario =
                        false

                    empleadoSeleccionado =
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
fun tarjetapersonalresponsable(
    empleado: PersonalResponsable,
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
                        "${empleado.nombre} ${empleado.apellido}",

                    fontSize = 18.sp,

                    fontWeight =
                        FontWeight.Bold,

                    modifier =
                        Modifier.weight(1f),

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text = "›",
                    fontSize = 26.sp,
                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text =
                    empleado.tipoPersonal,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Text(
                text =
                    empleado.correo,

                fontSize = 13.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(3.dp)
            )


            Text(
                text =
                    empleado.sala,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text =
                    empleado.estatus,

                fontSize = 13.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    if (
                        empleado.estatus ==
                        "Activo"
                    ) {

                        MaterialTheme.colorScheme.primary

                    } else {

                        MaterialTheme.colorScheme.error
                    }
            )
        }
    }
}


@Composable
fun formulariopersonalresponsable(
    empleado: PersonalResponsable?,
    correosExistentes: List<String>,
    guardar: (PersonalResponsable) -> Unit,
    darDeBaja: () -> Unit,
    cancelar: () -> Unit
) {

    var nombre by remember(empleado) {

        mutableStateOf(
            empleado?.nombre ?: ""
        )
    }


    var apellido by remember(empleado) {

        mutableStateOf(
            empleado?.apellido ?: ""
        )
    }


    var correo by remember(empleado) {

        mutableStateOf(
            empleado?.correo ?: ""
        )
    }


    var tipoPersonal by remember(empleado) {

        mutableStateOf(
            empleado?.tipoPersonal
                ?: "Asistente educativa"
        )
    }


    var sala by remember(empleado) {

        mutableStateOf(
            empleado?.sala
                ?: "Sala A"
        )
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
                    if (
                        empleado == null
                    ) {

                        "Agregar personal"

                    } else {

                        "Editar personal"
                    },

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            OutlinedTextField(
                value = nombre,

                onValueChange = {
                    nombre = it
                    mensaje = ""
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Nombre")
                }
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            OutlinedTextField(
                value = apellido,

                onValueChange = {
                    apellido = it
                    mensaje = ""
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Apellido")
                }
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            OutlinedTextField(
                value = correo,

                onValueChange = {
                    correo = it
                    mensaje = ""
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text(
                        "Correo institucional"
                    )
                }
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            selectorpersonal(
                titulo =
                    "Tipo de personal",

                valor =
                    tipoPersonal,

                opciones =
                    listOf(
                        "Asistente educativa",
                        "Maestra",
                        "Auxiliar"
                    ),

                seleccionar = {

                    tipoPersonal = it
                }
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            selectorpersonal(
                titulo = "Sala",

                valor = sala,

                opciones =
                    listOf(
                        "Sala A",
                        "Sala B",
                        "Sala C"
                    ),

                seleccionar = {

                    sala = it
                }
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            Text(
                text =
                    "Estancia asignada: Estancia 2",

                fontSize = 13.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.primary
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text =
                    "El turno se administra desde la sección Horarios.",

                fontSize = 12.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            if (
                mensaje != ""
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
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
                modifier = Modifier.height(20.dp)
            )


            Button(
                onClick = {

                    val correoDuplicado =
                        correosExistentes.any {

                            it.equals(
                                correo,
                                ignoreCase = true
                            )
                                    &&
                                    it != empleado?.correo
                        }


                    if (
                        nombre.isBlank()
                    ) {

                        mensaje =
                            "Falta ingresar el nombre"

                    } else if (
                        apellido.isBlank()
                    ) {

                        mensaje =
                            "Falta ingresar el apellido"

                    } else if (
                        correo.isBlank()
                    ) {

                        mensaje =
                            "Falta ingresar el correo"

                    } else if (
                        !correo.contains("@")
                    ) {

                        mensaje =
                            "Ingresa un correo válido"

                    } else if (
                        correoDuplicado
                    ) {

                        mensaje =
                            "Ese correo ya está registrado"

                    } else {

                        guardar(

                            PersonalResponsable(
                                id =
                                    empleado?.id ?: 0,

                                nombre =
                                    nombre,

                                apellido =
                                    apellido,

                                correo =
                                    correo,

                                tipoPersonal =
                                    tipoPersonal,

                                sala =
                                    sala,

                                estatus =
                                    empleado?.estatus
                                        ?: "Activo"
                            )
                        )
                    }
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        if (
                            empleado == null
                        ) {

                            "Agregar personal"

                        } else {

                            "Guardar cambios"
                        }
                )
            }


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            if (
                empleado != null
                &&
                empleado.estatus != "Baja"
            ) {

                Button(
                    onClick = {

                        darDeBaja()
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            "Dar de baja"
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )
            }


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
fun selectorpersonal(
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