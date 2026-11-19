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
import com.example.staffnest.model.SalaPresidencia


@Composable
fun detallesala(
    sala: SalaPresidencia,
    regresar: () -> Unit
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
                    text = sala.nombre,
                    fontSize = 26.sp,
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
                        24.dp
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
                            "Resumen de la sala",
                        fontSize = 21.sp,
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
                                15.dp
                            )
                    )


                    Text(
                        text =
                            "Personal actual: ${sala.personalActual}",
                        fontSize = 17.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )


                    Text(
                        text =
                            "Personal requerido: ${sala.personalRequerido}",
                        fontSize = 17.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )


                    Spacer(
                        modifier =
                            Modifier.height(
                                15.dp
                            )
                    )


                    Box(
                        modifier = Modifier
                            .background(
                                color,
                                RoundedCornerShape(
                                    15.dp
                                )
                            )
                            .padding(
                                horizontal =
                                    15.dp,
                                vertical =
                                    8.dp
                            )
                    ) {

                        Text(
                            text = estado,
                            color =
                                Color.White,
                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }
            }
        }


        item {

            Text(
                text =
                    "Personal asignado",
                fontSize = 25.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }


        items(
            sala.empleados
        ) { empleado ->


            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(
                        18.dp
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .primary
                                .copy(
                                    alpha = 0.08f
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
                            .size(45.dp)
                            .background(
                                MaterialTheme
                                    .colorScheme
                                    .primary,
                                RoundedCornerShape(
                                    30.dp
                                )
                            ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Text(
                            text =
                                empleado
                                    .first()
                                    .toString(),

                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onPrimary,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }


                    Spacer(
                        modifier =
                            Modifier.size(
                                15.dp
                            )
                    )


                    Text(
                        text = empleado,
                        fontSize = 17.sp,
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