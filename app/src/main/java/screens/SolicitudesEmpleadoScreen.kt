package com.example.staffnest.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun solicitudesempleado() {
    var tipoSolicitud by remember {
        mutableStateOf("")
    }
    var fecha by remember {
        mutableStateOf("")
    }
    var fechaFin by remember {
        mutableStateOf("")
    }
    var horaSalida by remember {
        mutableStateOf("")
    }
    var motivo by remember {
        mutableStateOf("")
    }
    var mensaje by remember {
        mutableStateOf("")
    }
    val finTurno =
        "16:00"

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
            text = "Solicitudes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "¿Qué deseas solicitar?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        tarjetasolicitud(
            titulo = "Salida temprana",
            descripcion =
                "Solicita salir antes de tu horario programado."
        ) {

            tipoSolicitud = "salida"
            fecha = ""
            fechaFin = ""
            horaSalida = ""
            motivo = ""
            mensaje = ""
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        tarjetasolicitud(
            titulo = "Vacaciones",
            descripcion =
                "Solicita uno o varios días de vacaciones."
        ) {

            tipoSolicitud = "vacaciones"

            fecha = ""
            fechaFin = ""
            horaSalida = ""
            motivo = ""
            mensaje = ""
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        tarjetasolicitud(
            titulo = "Permiso",
            descripcion =
                "Solicita autorización para faltar un día."
        ) {

            tipoSolicitud = "permiso"

            fecha = ""
            fechaFin = ""
            horaSalida = ""
            motivo = ""
            mensaje = ""
        }


        if (tipoSolicitud != "") {

            Spacer(
                modifier = Modifier.height(25.dp)
            )


            formulariosolicitud(
                tipoSolicitud = tipoSolicitud,

                fecha = fecha,

                cambiarFecha = {
                    fecha = it
                    mensaje = ""
                },

                fechaFin = fechaFin,

                cambiarFechaFin = {
                    fechaFin = it
                    mensaje = ""
                },

                horaSalida = horaSalida,

                cambiarHoraSalida = {
                    horaSalida = it
                    mensaje = ""
                },

                motivo = motivo,

                cambiarMotivo = {
                    motivo = it
                    mensaje = ""
                },

                finTurno = finTurno,

                enviar = {

                    if (
                        fecha.isBlank()
                    ) {

                        mensaje =
                            "Falta seleccionar la fecha"

                    } else if (
                        tipoSolicitud == "vacaciones"
                        && fechaFin.isBlank()
                    ) {

                        mensaje =
                            "Falta seleccionar la fecha final"

                    } else if (
                        tipoSolicitud == "vacaciones"
                        && !fechavalida(
                            fechaInicio = fecha,
                            fechaFin = fechaFin
                        )
                    ) {

                        mensaje =
                            "La fecha final no puede ser anterior a la fecha de inicio"

                    } else if (
                        tipoSolicitud == "salida"
                        && horaSalida.isBlank()
                    ) {

                        mensaje =
                            "Falta seleccionar la hora de salida"

                    } else if (
                        tipoSolicitud == "salida"
                        && !horavalida(
                            horaSalida = horaSalida,
                            finTurno = finTurno
                        )
                    ) {

                        mensaje =
                            "La hora solicitada debe ser anterior a tu hora de salida programada ($finTurno)"

                    } else if (
                        motivo.isBlank()
                    ) {

                        mensaje =
                            "Falta ingresar el motivo"

                    } else {

                        mensaje =
                            "Solicitud enviada a Presidencia"
                    }
                }
            )


            if (
                mensaje != ""
            ) {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                Text(
                    text = mensaje,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }
        }


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Text(
            text = "Mis solicitudes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme.colorScheme.onBackground
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        solicitudguardada(
            tipo = "Vacaciones",
            fecha =
                "15/12/2026 - 20/12/2026",
            estado = "Pendiente",
            motivo =
                "Viaje familiar.",
            comentario = ""
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        solicitudguardada(
            tipo = "Salida temprana",
            fecha =
                "23/11/2026 - 14:00",
            estado = "Aprobada",
            motivo =
                "Cita personal.",
            comentario =
                "Solicitud autorizada."
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        solicitudguardada(
            tipo = "Permiso",
            fecha =
                "28/11/2026",
            estado = "Rechazada",
            motivo =
                "Compromiso personal.",
            comentario =
                "No es posible autorizar el permiso en esa fecha."
        )


        Spacer(
            modifier = Modifier.height(100.dp)
        )
    }
}


@Composable
fun tarjetasolicitud(
    titulo: String,
    descripcion: String,
    accion: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                accion()
            },

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
            )
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(5.dp)
            )


            Text(
                text = descripcion,
                fontSize = 14.sp,
                color =
                    MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun formulariosolicitud(
    tipoSolicitud: String,
    fecha: String,
    cambiarFecha: (String) -> Unit,
    fechaFin: String,
    cambiarFechaFin: (String) -> Unit,
    horaSalida: String,
    cambiarHoraSalida: (String) -> Unit,
    motivo: String,
    cambiarMotivo: (String) -> Unit,
    finTurno: String,
    enviar: () -> Unit
) {

    val context =
        LocalContext.current


    val calendario =
        Calendar.getInstance()


    val selectorFecha =
        DatePickerDialog(
            context,

            { _, year, month, day ->

                val dia =
                    day
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                val mes =
                    (month + 1)
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                cambiarFecha(
                    "$dia/$mes/$year"
                )
            },

            calendario.get(
                Calendar.YEAR
            ),

            calendario.get(
                Calendar.MONTH
            ),

            calendario.get(
                Calendar.DAY_OF_MONTH
            )
        )


    val selectorFechaFin =
        DatePickerDialog(
            context,

            { _, year, month, day ->

                val dia =
                    day
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                val mes =
                    (month + 1)
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                cambiarFechaFin(
                    "$dia/$mes/$year"
                )
            },

            calendario.get(
                Calendar.YEAR
            ),

            calendario.get(
                Calendar.MONTH
            ),

            calendario.get(
                Calendar.DAY_OF_MONTH
            )
        )


    val selectorHora =
        TimePickerDialog(
            context,

            { _, hour, minute ->

                val hora =
                    hour
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                val minuto =
                    minute
                        .toString()
                        .padStart(
                            2,
                            '0'
                        )

                cambiarHoraSalida(
                    "$hora:$minuto"
                )
            },

            14,

            0,

            true
        )


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
                    when (
                        tipoSolicitud
                    ) {

                        "salida" ->
                            "Solicitud de salida temprana"

                        "vacaciones" ->
                            "Solicitud de vacaciones"

                        "permiso" ->
                            "Solicitud de permiso"

                        else ->
                            "Solicitud"
                    },

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


            Button(
                onClick = {

                    selectorFecha.show()
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        if (
                            fecha == ""
                        ) {

                            if (
                                tipoSolicitud ==
                                "vacaciones"
                            ) {

                                "Seleccionar fecha de inicio"

                            } else {

                                "Seleccionar fecha"
                            }

                        } else {

                            fecha
                        }
                )
            }


            if (
                tipoSolicitud ==
                "vacaciones"
            ) {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                Button(
                    onClick = {

                        selectorFechaFin.show()
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            if (
                                fechaFin == ""
                            ) {

                                "Seleccionar fecha final"

                            } else {

                                fechaFin
                            }
                    )
                }
            }


            if (
                tipoSolicitud ==
                "salida"
            ) {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                Button(
                    onClick = {

                        selectorHora.show()
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text =
                            if (
                                horaSalida == ""
                            ) {

                                "Seleccionar hora de salida"

                            } else {

                                horaSalida
                            }
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )


                Text(
                    text =
                        "Tu salida programada es a las $finTurno",

                    fontSize = 12.sp,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )
            }


            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            OutlinedTextField(
                value = motivo,

                onValueChange = {

                    cambiarMotivo(it)
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {

                    Text(
                        "Motivo"
                    )
                },

                minLines = 3,

                maxLines = 5
            )


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            Text(
                text =
                    "La solicitud será enviada a Presidencia para su revisión.",

                fontSize = 13.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            Button(
                onClick = {

                    enviar()
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "Enviar solicitud"
                )
            }
        }
    }
}


@Composable
fun solicitudguardada(
    tipo: String,
    fecha: String,
    estado: String,
    motivo: String,
    comentario: String
) {

    var mostrarDetalle by remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                mostrarDetalle =
                    !mostrarDetalle
            },

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
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
                    text = tipo,

                    fontSize = 17.sp,

                    fontWeight =
                        FontWeight.Bold,

                    modifier =
                        Modifier.weight(1f),

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text = estado,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )


            Text(
                text = fecha,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            if (
                mostrarDetalle
            ) {

                Spacer(
                    modifier =
                        Modifier.height(15.dp)
                )


                Text(
                    text = "Motivo",

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Text(
                    text = motivo,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )


                Text(
                    text =
                        "Estado: $estado",

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )


                if (
                    comentario != ""
                ) {

                    Spacer(
                        modifier =
                            Modifier.height(10.dp)
                    )


                    Text(
                        text =
                            "Comentario de Presidencia",

                        fontWeight =
                            FontWeight.Bold,

                        color =
                            MaterialTheme.colorScheme.onBackground
                    )


                    Spacer(
                        modifier =
                            Modifier.height(3.dp)
                    )


                    Text(
                        text =
                            comentario,

                        color =
                            MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}


fun fechavalida(
    fechaInicio: String,
    fechaFin: String
): Boolean {

    return try {

        val formato =
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

        formato.isLenient =
            false


        val inicio =
            formato.parse(
                fechaInicio
            )

        val fin =
            formato.parse(
                fechaFin
            )


        if (
            inicio == null
            || fin == null
        ) {

            false

        } else {

            !fin.before(
                inicio
            )
        }

    } catch (
        e: Exception
    ) {

        false
    }
}


fun horavalida(
    horaSalida: String,
    finTurno: String
): Boolean {

    return try {

        val salidaPartes =
            horaSalida.split(":")

        val finPartes =
            finTurno.split(":")


        val salidaMinutos =
            salidaPartes[0].toInt() * 60 +
                    salidaPartes[1].toInt()


        val finMinutos =
            finPartes[0].toInt() * 60 +
                    finPartes[1].toInt()


        salidaMinutos <
                finMinutos

    } catch (
        e: Exception
    ) {

        false
    }
}