package com.example.staffnest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.EstanciaPresidencia
import com.example.staffnest.model.SalaPresidencia


@Composable
fun detalleestancia(
    estancia: EstanciaPresidencia,
    regresar: () -> Unit,
    abrirSala: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp),

        verticalArrangement =
            Arrangement.spacedBy(15.dp)
    ) {


        item {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "←",
                    fontSize = 30.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground,

                    modifier = Modifier
                        .clickable {
                            regresar()
                        }
                        .padding(8.dp)
                )


                Text(
                    text = estancia.nombre,
                    fontSize = 30.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }
        }


        item {

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(
                        25.dp
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .primary
                                .copy(
                                    alpha = 0.10f
                                )
                    )
            ) {


                Column(
                    modifier =
                        Modifier.padding(
                            20.dp
                        )
                ) {


                    Text(
                        text =
                            "Resumen de la estancia",
                        fontSize = 22.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )


                    Spacer(
                        modifier =
                            Modifier.height(
                                20.dp
                            )
                    )


                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(
                                12.dp
                            )
                    ) {

                        resumenestancia(
                            numero =
                                estancia.empleados
                                    .toString(),
                            texto =
                                "Empleados",
                            modifier =
                                Modifier.weight(1f)
                        )


                        resumenestancia(
                            numero =
                                estancia.totalSalas
                                    .toString(),
                            texto =
                                "Salas",
                            modifier =
                                Modifier.weight(1f)
                        )
                    }


                    Spacer(
                        modifier =
                            Modifier.height(
                                12.dp
                            )
                    )


                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(
                                12.dp
                            )
                    ) {

                        resumenestancia(
                            numero =
                                estancia.incidenciasHoy
                                    .toString(),
                            texto =
                                "Incidencia hoy",
                            modifier =
                                Modifier.weight(1f)
                        )


                        resumenestancia(
                            numero =
                                estancia.salasConAlerta
                                    .toString(),
                            texto =
                                "Sala con alerta",
                            modifier =
                                Modifier.weight(1f)
                        )
                    }
                }
            }
        }


        item {

            Text(
                text =
                    "Estado de salas",
                fontSize = 26.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }


        items(
            estancia.salas
        ) { sala ->

            tarjetasala(
                sala = sala,

                abrir = {
                    abrirSala(
                        sala.id
                    )
                }
            )
        }


        item {

            Spacer(
                modifier =
                    Modifier.height(
                        30.dp
                    )
            )
        }
    }
}


@Composable
fun resumenestancia(
    numero: String,
    texto: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape =
            RoundedCornerShape(
                20.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .primary
                        .copy(
                            alpha = 0.13f
                        )
            )
    ) {


        Column(
            modifier =
                Modifier.padding(
                    18.dp
                )
        ) {

            Text(
                text = numero,
                fontSize = 26.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )


            Text(
                text = texto,
                fontSize = 14.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }
    }
}


@Composable
fun tarjetasala(
    sala: SalaPresidencia,
    abrir: () -> Unit
) {

    val completa =
        sala.personalActual >=
                sala.personalRequerido


    val color =
        if (completa) {

            Color(0xFF3BAA63)

        } else {

            Color(0xFFE85B5B)
        }


    val estado =
        if (completa) {

            "Personal completo"

        } else {

            "Personal insuficiente"
        }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                abrir()
            },

        shape =
            RoundedCornerShape(
                22.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .primary
                        .copy(
                            alpha = 0.10f
                        )
            )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {


            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(
                        color,
                        RoundedCornerShape(
                            30.dp
                        )
                    )
            )


            Spacer(
                modifier =
                    Modifier.size(
                        15.dp
                    )
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = sala.nombre,
                    fontSize = 18.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )


                Text(
                    text =
                        "${sala.personalActual}/${sala.personalRequerido} - $estado",
                    fontSize = 14.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }


            Text(
                text = "›",
                fontSize = 32.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }
    }
}