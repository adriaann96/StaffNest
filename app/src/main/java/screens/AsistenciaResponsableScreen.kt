package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun asistenciaresponsable(
    regresar: () -> Unit
) {
    var codigoEntrada by remember {
        mutableStateOf("")
    }
    var codigoSalida by remember {
        mutableStateOf("")
    }
    var tiempoEntrada by remember {
        mutableStateOf(0)
    }
    var tiempoSalida by remember {
        mutableStateOf(0)
    }

    // Temporizador de entrada
    LaunchedEffect(codigoEntrada) {
        if (codigoEntrada.isNotEmpty()) {
            tiempoEntrada = 60
            while (tiempoEntrada > 0) {

                delay(1000)

                tiempoEntrada--
            }

            codigoEntrada = ""
        }
    }


    // Temporizador de salida
    LaunchedEffect(codigoSalida) {

        if (codigoSalida.isNotEmpty()) {

            tiempoSalida = 60

            while (tiempoSalida > 0) {

                delay(1000)

                tiempoSalida--
            }

            codigoSalida = ""
        }
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
            text = "← Regresar",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    regresar()
                }
                .padding(bottom = 15.dp)
        )


        Text(
            text = "Registrar asistencia",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "Estancia 2",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "Genera un código temporal para que los empleados registren su entrada o salida.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        tarjetacodigoasistencia(
            titulo = "Código de entrada",
            descripcion = "Los empleados utilizarán este código para registrar su entrada.",
            codigo = codigoEntrada,
            tiempo = tiempoEntrada,
            generar = {

                codigoEntrada = generarcodigo(
                    codigoDiferente = codigoSalida
                )
            }
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        tarjetacodigoasistencia(
            titulo = "Código de salida",
            descripcion = "Los empleados utilizarán este código para registrar su salida.",
            codigo = codigoSalida,
            tiempo = tiempoSalida,
            generar = {

                codigoSalida = generarcodigo(
                    codigoDiferente = codigoEntrada
                )
            }
        )


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.08f
                    )
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Importante",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = "Cada código es válido durante 60 segundos.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "El código de entrada y el código de salida siempre son diferentes.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                Text(
                    text = "Cuando un código expire será necesario generar uno nuevo.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }


        Spacer(
            modifier = Modifier.height(100.dp)
        )
    }
}


@Composable
fun tarjetacodigoasistencia(
    titulo: String,
    descripcion: String,
    codigo: String,
    tiempo: Int,
    generar: () -> Unit
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
                text = titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text = descripcion,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            if (codigo.isEmpty()) {

                Text(
                    text = "No hay código activo",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

            } else {

                Text(
                    text = codigo,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )


                Spacer(
                    modifier = Modifier.height(5.dp)
                )


                Text(
                    text = "Expira en $tiempo segundos",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Button(
                onClick = {
                    generar()
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        if (codigo.isEmpty()) {
                            "Generar código"
                        } else {
                            "Generar nuevo código"
                        }
                )
            }
        }
    }
}


fun generarcodigo(
    codigoDiferente: String
): String {

    var codigo: String

    do {

        codigo = Random
            .nextInt(
                10000,
                100000
            )
            .toString()

    } while (
        codigo == codigoDiferente
    )


    return codigo
}