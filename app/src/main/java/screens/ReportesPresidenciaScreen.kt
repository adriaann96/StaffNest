package com.example.staffnest.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.listaEstanciasPresidencia


@Composable
fun reportespresidencia() {

    val contexto = LocalContext.current

    var contenidoCsv by remember {
        mutableStateOf("")
    }


    val guardarArchivo =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.CreateDocument(
                    "text/csv"
                )
        ) { uri ->

            if (uri != null) {

                contexto.contentResolver
                    .openOutputStream(uri)
                    ?.bufferedWriter()
                    ?.use {

                        it.write(
                            contenidoCsv
                        )
                    }
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
            text = "Reportes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier =
                Modifier.height(25.dp)
        )


        tarjetareporte(
            titulo = "Asistencias",
            descripcion =
                "Consulta y exporta registros de asistencia.",
            icono = "✓"
        ) {

            contenidoCsv =
                csvAsistencias()

            guardarArchivo.launch(
                "asistencias.csv"
            )
        }


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        tarjetareporte(
            titulo = "Incidencias",
            descripcion =
                "Consulta y exporta incidencias.",
            icono = "!"
        ) {

            contenidoCsv =
                csvIncidencias()

            guardarArchivo.launch(
                "incidencias.csv"
            )
        }


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        tarjetareporte(
            titulo = "Ausencias",
            descripcion =
                "Consulta y exporta ausencias.",
            icono = "A"
        ) {

            contenidoCsv =
                csvAusencias()

            guardarArchivo.launch(
                "ausencias.csv"
            )
        }


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


        tarjetareporte(
            titulo =
                "Movimientos de personal",
            descripcion =
                "Altas, bajas y traslados.",
            icono = "P"
        ) {

            contenidoCsv =
                csvMovimientos()

            guardarArchivo.launch(
                "movimientos_personal.csv"
            )
        }


        Spacer(
            modifier =
                Modifier.height(30.dp)
        )


        Text(
            text =
                "Formato de exportación",
            fontSize = 20.sp,
            fontWeight =
                FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier =
                Modifier.height(15.dp)
        )


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
                                alpha = 0.10f
                            )
                )
        ) {

            Text(
                text =
                    "● CSV",

                modifier =
                    Modifier.padding(
                        18.dp
                    ),

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }


        Spacer(
            modifier =
                Modifier.height(80.dp)
        )
    }
}


@Composable
fun tarjetareporte(
    titulo: String,
    descripcion: String,
    icono: String,
    descargar: () -> Unit
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

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
                    .size(55.dp)
                    .background(
                        MaterialTheme
                            .colorScheme
                            .primary,
                        RoundedCornerShape(
                            15.dp
                        )
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = icono,
                    fontSize = 22.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimary
                )
            }


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
                    text = titulo,
                    fontSize = 18.sp,
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
                            4.dp
                        )
                )


                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }


            Button(
                onClick = {
                    descargar()
                },

                shape =
                    RoundedCornerShape(
                        15.dp
                    ),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .secondary
                    )
            ) {

                Text(
                    text = "↓",
                    fontSize = 20.sp,
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
}
fun csvAsistencias(): String {

    var csv =
        "Estancia,Sala,Empleado,Estatus\n"


    listaEstanciasPresidencia.forEach { estancia ->

        estancia.salas.forEach { sala ->

            sala.empleados.forEach { empleado ->

                csv +=
                    "${estancia.nombre}," +
                            "${sala.nombre}," +
                            "$empleado," +
                            "A tiempo\n"
            }
        }
    }

    return csv
}


fun csvIncidencias(): String {

    var csv =
        "Estancia,Incidencias hoy,Salas con alerta\n"


    listaEstanciasPresidencia.forEach { estancia ->

        csv +=
            "${estancia.nombre}," +
                    "${estancia.incidenciasHoy}," +
                    "${estancia.salasConAlerta}\n"
    }
    return csv
}

fun csvAusencias(): String {
    return """
Empleado,Tipo,Fecha inicio,Fecha fin
Ana López,Permiso,2026-11-18,2026-11-18
María García,Vacaciones,2026-11-15,2026-11-20
Andrea Torres,Permiso,2026-11-19,2026-11-19
""".trimIndent()
}


fun csvMovimientos(): String {
    return """
Empleado,Movimiento,Estancia,Fecha
Ana López,Alta,Estancia 1,2026-11-01
María García,Traslado,Estancia 2,2026-11-05
Andrea Torres,Baja,Estancia 3,2026-11-10
""".trimIndent()
}