package com.example.staffnest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.staffnest.ui.theme.StaffNestTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.Box
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            StaffNestTheme {
                navegacion()
            }
        }
    }
}

@Composable
fun navegacion() {
    val nav = rememberNavController()
    NavHost(
        navController = nav,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            inicio(
                responsable = {
                    nav.navigate("responsable")
                },
                presidencia = {
                    nav.navigate("presidencia")
                },
                coordinador = {
                    nav.navigate("coordinador")
                },
                empleado = {
                    nav.navigate("empleado")
                }
            )
        }
        composable("responsable") {
            responsable(
                cerrarsesion = {
                    nav.navigate("inicio")
                }
            )
        }
        composable("presidencia") {
            presidencia(
                cerrarsesion = {
                    nav.navigate("inicio")
                }
            )
        }
        composable("coordinador") {
            coordinador(
                cerrarsesion = {
                    nav.navigate("inicio")
                }
            )
        }
        composable("empleado") {
            empleado(
                cerrarsesion = {
                    nav.navigate("inicio")
                }
            )
        }
    }
}

@Composable
fun inicio(responsable: () -> Unit, presidencia: () -> Unit,
    coordinador: () -> Unit, empleado: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var aceptado by remember { mutableStateOf(false) }
    var perfil by remember { mutableStateOf("Responsable") }
    var menu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F6FD0))
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "✿",
            fontSize = 70.sp,
            color = Color(0xFFD8C4FF)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Bienvenido!!!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFC83D)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Usuario")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text("Contraseña")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = aceptado,
                        onCheckedChange = { aceptado = it }
                    )
                    Text("Acepto aviso de privacidad")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text("Perfil")
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { menu = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(perfil)
                    }
                    DropdownMenu(
                        expanded = menu,
                        onDismissRequest = { menu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Responsable") },
                            onClick = {
                                perfil = "Responsable"
                                menu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Presidencia") },
                            onClick = {
                                perfil = "Presidencia"
                                menu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Coordinador") },
                            onClick = {
                                perfil = "Coordinador"
                                menu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Empleado") },
                            onClick = {
                                perfil = "Empleado"
                                menu = false
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = {
                        if (perfil == "Responsable") {
                            responsable()
                        }
                        if (perfil == "Presidencia") {
                            presidencia()
                        }
                        if (perfil == "Coordinador") {
                            coordinador()
                        }
                        if (perfil == "Empleado") {
                            empleado()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = aceptado
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Problemas para iniciar sesión? Guía de ayuda",
                    fontSize = 12.sp
                )
            }
        }
    }
}
@Composable
fun responsable(
    cerrarsesion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F6FD0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Responsable",
            color = Color.White,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                cerrarsesion()
            }
        ) {
            Text("Cerrar sesión")
        }
    }
}
@Composable
fun presidencia(
    cerrarsesion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F6FD0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Presidencia",
            color = Color.White,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                cerrarsesion()
            }
        ) {
            Text("Cerrar sesión")
        }
    }
}
@Composable
fun coordinador(
    cerrarsesion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F6FD0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Coordinador",
            color = Color.White,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                cerrarsesion()
            }
        ) {
            Text("Cerrar sesión")
        }
    }
}
@Composable
fun empleado(
    cerrarsesion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F6FD0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pantalla Empleado",
            color = Color.White,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                cerrarsesion()
            }
        ) {
            Text("Cerrar sesión")
        }
    }
}
