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
import androidx.compose.material3.TextField
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
import com.example.staffnest.model.EstanciaPresidencia
import com.example.staffnest.model.listaEstanciasPresidencia


@Composable
fun estanciaspresidencia(
    abrirEstancia: (Int) -> Unit
) {

    var buscar by remember {
        mutableStateOf("")
    }


    val estanciasFiltradas =
        listaEstanciasPresidencia.filter {

            it.nombre.contains(
                buscar,
                ignoreCase = true
            )
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

            Text(
                text = "Todas las Estancias",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            TextField(
                value = buscar,

                onValueChange = {
                    buscar = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        "Buscar estancia..."
                    )
                },

                singleLine = true
            )
        }


        items(
            estanciasFiltradas
        ) { estancia ->

            tarjetaestancia(
                estancia = estancia,

                abrir = {
                    abrirEstancia(
                        estancia.id
                    )
                }
            )
        }


        item {

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )
        }
    }
}


@Composable
fun tarjetaestancia(
    estancia: EstanciaPresidencia,
    abrir: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                abrir()
            },

        shape =
            RoundedCornerShape(24.dp),

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
                    .size(70.dp)
                    .background(
                        MaterialTheme
                            .colorScheme
                            .primary,
                        RoundedCornerShape(
                            20.dp
                        )
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "⌂",
                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimary,
                    fontSize = 32.sp
                )
            }


            Spacer(
                modifier =
                    Modifier.size(15.dp)
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = estancia.nombre,
                    fontSize = 20.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )


                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )


                Text(
                    text =
                        "${estancia.empleados} empleados",
                    fontSize = 15.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }


            estadocobertura(
                alertas =
                    estancia.salasConAlerta
            )


            Text(
                text = "›",
                fontSize = 32.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                modifier =
                    Modifier.padding(
                        start = 10.dp
                    )
            )
        }
    }
}


@Composable
fun estadocobertura(
    alertas: Int
) {

    val texto: String
    val color: Color


    if (alertas == 0) {

        texto =
            "Cobertura OK"

        color =
            Color(0xFF3BAA63)

    } else if (alertas == 1) {

        texto =
            "1 sala con alerta"

        color =
            Color(0xFFFFC83D)

    } else {

        texto =
            "$alertas salas con alerta"

        color =
            Color(0xFFE85B5B)
    }


    Box(
        modifier = Modifier
            .background(
                color,
                RoundedCornerShape(15.dp)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 9.dp
            )
    ) {

        Text(
            text = texto,
            color = Color.White,
            fontWeight =
                FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}