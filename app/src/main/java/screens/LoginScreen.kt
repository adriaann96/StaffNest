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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffnest.model.Perfil

@Composable
fun login(
    entrar: (Perfil) -> Unit
) {

    var usuario by remember {
        mutableStateOf("")
    }

    var contrasena by remember {
        mutableStateOf("")
    }

    var aceptado by remember {
        mutableStateOf(false)
    }

    var mensaje by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(35.dp)
        )


        Text(
            text = "✿",
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(
            text = "Bienvenido!!!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Correo institucional",
                    color = MaterialTheme.colorScheme.onSurface
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                TextField(
                    value = usuario,
                    onValueChange = {

                        usuario = it
                        mensaje = ""
                    },

                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                    modifier = Modifier.height(15.dp)
                )


                Text(
                    text = "Contraseña",
                    color = MaterialTheme.colorScheme.onSurface
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                TextField(
                    value = contrasena,
                    onValueChange = {

                        contrasena = it
                        mensaje = ""
                    },

                    modifier = Modifier.fillMaxWidth(),

                    visualTransformation =
                        PasswordVisualTransformation()
                )


                Spacer(
                    modifier = Modifier.height(15.dp)
                )


                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = aceptado,

                        onCheckedChange = {
                            aceptado = it
                        }
                    )


                    Text(
                        text = "Acepto aviso de privacidad",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                Button(
                    onClick = {

                        if (usuario.isBlank()) {

                            mensaje =
                                "Falta ingresar el correo"

                        } else if (contrasena.isBlank()) {

                            mensaje =
                                "Falta ingresar la contraseña"

                        } else {

                            when {

                                usuario ==
                                        "presidencia@presidencia.com" &&
                                        contrasena ==
                                        "presi123" -> {

                                    entrar(
                                        Perfil.PRESIDENCIA
                                    )
                                }


                                usuario ==
                                        "responsable@responsable.com" &&
                                        contrasena ==
                                        "respon123" -> {

                                    entrar(
                                        Perfil.RESPONSABLE
                                    )
                                }


                                usuario ==
                                        "coordinador@coordinador.com" &&
                                        contrasena ==
                                        "coord123" -> {

                                    entrar(
                                        Perfil.COORDINADOR
                                    )
                                }


                                usuario ==
                                        "empleado@empleado.com" &&
                                        contrasena ==
                                        "empleado123" -> {

                                    entrar(
                                        Perfil.EMPLEADO
                                    )
                                }


                                else -> {

                                    mensaje =
                                        "Correo o contraseña incorrectos"
                                }
                            }
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),

                    enabled = aceptado
                ) {

                    Text(
                        text = "Iniciar sesión"
                    )
                }


                if (mensaje.isNotEmpty()) {

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )


                    Text(
                        text = mensaje,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(
                    modifier = Modifier.height(15.dp)
                )


                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}