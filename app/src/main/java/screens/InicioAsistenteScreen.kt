
package com.example.staffnest.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.listaEstanciasPresidencia

@Composable
fun inicioasistente(
    verSalas: Boolean,
    gestionarPersonal: Boolean,
    gestionarHorarios: Boolean,
    gestionarIncidencias: Boolean,
    puedeRegistrarAsistencia: Boolean,
    registrarAsistencia: () -> Unit,
    verSala: (Int) -> Unit
) {

    // Estancia hardcodeada, dinamizar con próximo avance.

    val estancia =
        listaEstanciasPresidencia.first {
            it.id == 2
        }

    // Dato temporal.
    val empleadosPresentes = 26

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
            text = "Mi estancia",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        Text(
            text = estancia.nombre,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        Text(
            text = "Perfil de asistente",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        if (
            gestionarPersonal ||
            puedeRegistrarAsistencia ||
            gestionarIncidencias ||
            verSalas
        ) {

            Text(
                text = "Resumen de mi estancia",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            if (
                gestionarPersonal ||
                puedeRegistrarAsistencia
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    if (gestionarPersonal) {
                        tarjetaresumenresponsable(
                            numero = estancia.empleados.toString(),
                            texto = "Empleados\nprogramados",
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 6.dp)
                        )
                    }

                    if (puedeRegistrarAsistencia) {
                        tarjetaresumenresponsable(
                            numero = empleadosPresentes.toString(),
                            texto = "Presentes\nhoy",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 6.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }

            if (gestionarIncidencias || verSalas) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    if (gestionarIncidencias) {
                        tarjetaresumenresponsable(
                            numero = estancia.incidenciasHoy.toString(),
                            texto = "Incidencias\nhoy",
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 6.dp)
                        )
                    }

                    if (verSalas) {
                        tarjetaresumenresponsable(
                            numero = estancia.salasConAlerta.toString(),
                            texto = "Salas con\nalerta",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 6.dp)
                        )
                    }
                }
            }
        }

        if (puedeRegistrarAsistencia) {

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            Button(
                onClick = {
                    registrarAsistencia()
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp)
            ) {

                Text(
                    text = "Registrar asistencia",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (verSalas) {

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = "Estado de salas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            estancia.salas.forEach { sala ->

                tarjetasalaresponsable(
                    nombre = sala.nombre,

                    actual = sala.personalActual,

                    requerido = sala.personalRequerido,

                    accion = {
                        verSala(sala.id)
                    }
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        Text(
            text = "Mis permisos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                permisosasistente(
                    texto = "Consultar salas",
                    autorizado = verSalas
                )

                permisosasistente(
                    texto = "Gestionar personal",
                    autorizado = gestionarPersonal
                )

                permisosasistente(
                    texto = "Gestionar horarios",
                    autorizado = gestionarHorarios
                )

                permisosasistente(
                    texto = "Gestionar incidencias",
                    autorizado = gestionarIncidencias
                )

                permisosasistente(
                    texto = "Registrar asistencia",
                    autorizado = puedeRegistrarAsistencia
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Los permisos son asignados por la responsable de la estancia.",
                    fontSize = 12.sp,
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
fun permisosasistente(
    texto: String,
    autorizado: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = texto,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = if (autorizado) {
                "Autorizado"
            } else {
                "Restringido"
            },

            fontSize = 13.sp,

            fontWeight = FontWeight.Bold,

            color = if (autorizado) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
    }
}
