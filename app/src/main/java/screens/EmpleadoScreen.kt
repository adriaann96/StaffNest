package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.staffnest.components.barrasuperior
import com.example.staffnest.components.botonmenu
import com.example.staffnest.navigation.RutaEmpleado
import kotlinx.coroutines.delay
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
@Composable
fun empleado(
    cerrarsesion: () -> Unit
) {

    val nav = rememberNavController()
    val entrada = nav.currentBackStackEntryAsState()
    val actual = entrada.value?.destination?.route

    Scaffold(

        topBar = {

            barrasuperior(
                cerrarsesion = cerrarsesion
            )
        },

        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {

                botonmenu(
                    texto = "Registro",
                    activo = actual == RutaEmpleado.Registro.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaEmpleado.Registro.ruta
                    )
                }

                botonmenu(
                    texto = "Mi horario",
                    activo = actual == RutaEmpleado.Horario.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaEmpleado.Horario.ruta
                    )
                }

                botonmenu(
                    texto = "Estadísticas",
                    activo = actual == RutaEmpleado.Estadisticas.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaEmpleado.Estadisticas.ruta
                    )
                }

                botonmenu(
                    texto = "Solicitudes",
                    activo = actual == RutaEmpleado.Solicitudes.ruta,
                    modifier = Modifier.weight(1f)
                ) {

                    nav.navigate(
                        RutaEmpleado.Solicitudes.ruta
                    )
                }
            }
        }

    ) { espacio ->

        NavHost(
            navController = nav,
            startDestination = RutaEmpleado.Registro.ruta,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(espacio)
        ) {

            composable(
                RutaEmpleado.Registro.ruta
            ) {

                registroempleado()
            }

            composable(
                RutaEmpleado.Horario.ruta
            ) {

                pantallavacia("Mi horario")
            }

            composable(
                RutaEmpleado.Estadisticas.ruta
            ) {

                pantallavacia("Estadísticas")
            }

            composable(
                RutaEmpleado.Solicitudes.ruta
            ) {

                pantallavacia("Solicitudes")
            }
        }
    }
}


@Composable
fun registroempleado() {

    var entradaRegistrada by remember {
        mutableStateOf(false)
    }
    var salidaRegistrada by remember {
        mutableStateOf(false)
    }
    var mostrarEntrada by remember {
        mutableStateOf(false)
    }
    var mostrarSalida by remember {
        mutableStateOf(false)
    }
    var codigo by remember {
        mutableStateOf("")
    }
    var segundos by remember {
        mutableStateOf(60)
    }
    var mensaje by remember {
        mutableStateOf("")
    }

    // Códigos de prueba por ahora
    val codigoEntrada = "12345"
    val codigoSalida = "67890"

    LaunchedEffect(
        mostrarEntrada,
        mostrarSalida
    ) {

        if (mostrarEntrada || mostrarSalida) {
            segundos = 60
            while (segundos > 0) {
                delay(1000)
                segundos--
            }
            codigo = ""
            mensaje = "El código expiró. Solicita un nuevo código."
            segundos = 60
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text(
            text = "Registro",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Registro de asistencia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text =
                        if (entradaRegistrada) {
                            "Entrada registrada"
                        } else {
                            "Entrada pendiente"
                        },
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text =
                        if (salidaRegistrada) {
                            "Salida registrada"
                        } else {
                            "Salida pendiente"
                        },
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Button(
            onClick = {

                mostrarEntrada = true
                mostrarSalida = false

                codigo = ""
                mensaje = ""
                segundos = 60
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !entradaRegistrada
        ) {

            Text("Registrar entrada")
        }


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        Button(
            onClick = {

                mostrarSalida = true
                mostrarEntrada = false

                codigo = ""
                mensaje = ""
                segundos = 60
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = entradaRegistrada && !salidaRegistrada
        ) {

            Text("Registrar salida")
        }


        if (mostrarEntrada || mostrarSalida) {

            Spacer(
                modifier = Modifier.height(30.dp)
            )


            Text(
                text =
                    if (mostrarEntrada) {
                        "Código de entrada"
                    } else {
                        "Código de salida"
                    },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            Text(
                text = "Ingresa el código de 5 dígitos",
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            BasicTextField(
                value = codigo,
                onValueChange = {

                    if (
                        it.length <= 5 &&
                        it.all { caracter ->
                            caracter.isDigit()
                        }
                    ) {

                        codigo = it
                        mensaje = ""
                    }
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                modifier = Modifier.fillMaxWidth(),

                decorationBox = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        for (i in 0..4) {

                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text =
                                        if (i < codigo.length) {
                                            codigo[i].toString()
                                        } else {
                                            ""
                                        },
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                }
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Text(
                text = "Tiempo restante: 00:${segundos.toString().padStart(2, '0')}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Button(
                onClick = {

                    if (mostrarEntrada) {

                        if (codigo == codigoEntrada) {

                            entradaRegistrada = true

                            mostrarEntrada = false

                            codigo = ""

                            mensaje = "Entrada registrada correctamente"

                        } else {

                            mensaje = "Código incorrecto"
                        }
                    }


                    if (mostrarSalida) {

                        if (codigo == codigoSalida) {

                            salidaRegistrada = true

                            mostrarSalida = false

                            codigo = ""

                            mensaje = "Salida registrada correctamente"

                        } else {

                            mensaje = "Código incorrecto"
                        }
                    }
                },

                modifier = Modifier.fillMaxWidth(),

                enabled = codigo.length == 5
            ) {

                Text("Confirmar código")
            }
        }


        if (mensaje.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Text(
                text = mensaje,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}