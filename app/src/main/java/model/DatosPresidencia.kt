package com.example.staffnest.model

data class SalaPresidencia(
    val id: Int,
    val nombre: String,
    val personalActual: Int,
    val personalRequerido: Int,
    val empleados: List<String>
)

data class EstanciaPresidencia(
    val id: Int,
    val nombre: String,
    val empleados: Int,
    val incidenciasHoy: Int,
    val salas: List<SalaPresidencia>
) {

    val totalSalas: Int
        get() = salas.size

    val salasConAlerta: Int
        get() = salas.count {
            it.personalActual < it.personalRequerido
        }
}

val listaEstanciasPresidencia = listOf(

    EstanciaPresidencia(
        id = 1,
        nombre = "Estancia 1",
        empleados = 32,
        incidenciasHoy = 0,
        salas = listOf(

            SalaPresidencia(
                id = 1,
                nombre = "Sala A",
                personalActual = 5,
                personalRequerido = 5,
                empleados = listOf(
                    "Ana López",
                    "María García",
                    "Sofía Martínez",
                    "Laura Hernández",
                    "Daniela Rodríguez"
                )
            ),

            SalaPresidencia(
                id = 2,
                nombre = "Sala B",
                personalActual = 4,
                personalRequerido = 4,
                empleados = listOf(
                    "Andrea Torres",
                    "Fernanda Ruiz",
                    "Carolina Díaz",
                    "Paola Flores"
                )
            ),

            SalaPresidencia(
                id = 3,
                nombre = "Sala C",
                personalActual = 3,
                personalRequerido = 3,
                empleados = listOf(
                    "Mónica Salas",
                    "Valeria Cruz",
                    "Alejandra Ramos"
                )
            )
        )
    ),

    EstanciaPresidencia(
        id = 2,
        nombre = "Estancia 2",
        empleados = 28,
        incidenciasHoy = 1,
        salas = listOf(

            SalaPresidencia(
                id = 1,
                nombre = "Sala A",
                personalActual = 5,
                personalRequerido = 5,
                empleados = listOf(
                    "Ana Pérez",
                    "Laura Ramírez",
                    "Sofía Flores",
                    "Mariana López",
                    "Daniela García"
                )
            ),

            SalaPresidencia(
                id = 2,
                nombre = "Sala B",
                personalActual = 3,
                personalRequerido = 5,
                empleados = listOf(
                    "Andrea Torres",
                    "Fernanda Ruiz",
                    "Carolina Díaz"
                )
            ),

            SalaPresidencia(
                id = 3,
                nombre = "Sala C",
                personalActual = 4,
                personalRequerido = 4,
                empleados = listOf(
                    "Paola Flores",
                    "Mónica Salas",
                    "Valeria Cruz",
                    "Alejandra Ramos"
                )
            )
        )
    ),

    EstanciaPresidencia(
        id = 3,
        nombre = "Estancia 3",
        empleados = 27,
        incidenciasHoy = 0,
        salas = listOf(

            SalaPresidencia(
                id = 1,
                nombre = "Sala A",
                personalActual = 4,
                personalRequerido = 4,
                empleados = listOf(
                    "Ana López",
                    "María García",
                    "Sofía Martínez",
                    "Laura Hernández"
                )
            ),

            SalaPresidencia(
                id = 2,
                nombre = "Sala B",
                personalActual = 5,
                personalRequerido = 5,
                empleados = listOf(
                    "Andrea Torres",
                    "Fernanda Ruiz",
                    "Carolina Díaz",
                    "Paola Flores",
                    "Mónica Salas"
                )
            ),

            SalaPresidencia(
                id = 3,
                nombre = "Sala C",
                personalActual = 3,
                personalRequerido = 3,
                empleados = listOf(
                    "Valeria Cruz",
                    "Alejandra Ramos",
                    "Daniela Rodríguez"
                )
            )
        )
    ),

    EstanciaPresidencia(
        id = 4,
        nombre = "Estancia 4",
        empleados = 31,
        incidenciasHoy = 0,
        salas = listOf(

            SalaPresidencia(
                id = 1,
                nombre = "Sala A",
                personalActual = 6,
                personalRequerido = 6,
                empleados = listOf(
                    "Ana López",
                    "María García",
                    "Sofía Martínez",
                    "Laura Hernández",
                    "Daniela Rodríguez",
                    "Andrea Torres"
                )
            ),

            SalaPresidencia(
                id = 2,
                nombre = "Sala B",
                personalActual = 4,
                personalRequerido = 4,
                empleados = listOf(
                    "Fernanda Ruiz",
                    "Carolina Díaz",
                    "Paola Flores",
                    "Mónica Salas"
                )
            ),

            SalaPresidencia(
                id = 3,
                nombre = "Sala C",
                personalActual = 5,
                personalRequerido = 5,
                empleados = listOf(
                    "Valeria Cruz",
                    "Alejandra Ramos",
                    "Daniela García",
                    "Mariana López",
                    "Sofía Flores"
                )
            )
        )
    ),

    EstanciaPresidencia(
        id = 5,
        nombre = "Estancia 5",
        empleados = 24,
        incidenciasHoy = 2,
        salas = listOf(

            SalaPresidencia(
                id = 1,
                nombre = "Sala A",
                personalActual = 2,
                personalRequerido = 4,
                empleados = listOf(
                    "Ana López",
                    "María García"
                )
            ),

            SalaPresidencia(
                id = 2,
                nombre = "Sala B",
                personalActual = 3,
                personalRequerido = 5,
                empleados = listOf(
                    "Andrea Torres",
                    "Fernanda Ruiz",
                    "Carolina Díaz"
                )
            ),

            SalaPresidencia(
                id = 3,
                nombre = "Sala C",
                personalActual = 4,
                personalRequerido = 4,
                empleados = listOf(
                    "Paola Flores",
                    "Mónica Salas",
                    "Valeria Cruz",
                    "Alejandra Ramos"
                )
            ),

            SalaPresidencia(
                id = 4,
                nombre = "Sala D",
                personalActual = 2,
                personalRequerido = 2,
                empleados = listOf(
                    "Daniela Rodríguez",
                    "Sofía Martínez"
                )
            )
        )
    )
)