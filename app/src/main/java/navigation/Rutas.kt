package com.example.staffnest.navigation

sealed class Ruta(val ruta: String) {
    object Login : Ruta("login")
    object Presidencia : Ruta("presidencia")
    object Responsable : Ruta("responsable")
    object Coordinador : Ruta("coordinador")
    object Empleado : Ruta("empleado")
}

sealed class RutaPresidencia(val ruta: String) {
    object Estancias : RutaPresidencia("estancias")
    object Estadisticas : RutaPresidencia("estadisticas")
    object Reportes : RutaPresidencia("reportes")
    object Personal : RutaPresidencia("personal")
}

sealed class RutaEmpleado(val ruta: String) {
    object Registro : RutaEmpleado("registro")
    object Horario : RutaEmpleado("horario")
    object Estadisticas : RutaEmpleado("estadisticas")
}

sealed class RutaResponsable(val ruta: String) {
    object Inicio : RutaResponsable("inicio")
    object Estancias : RutaResponsable("estancias")
    object Reportes : RutaResponsable("reportes")
    object Perfil : RutaResponsable("perfil")
}

sealed class RutaCoordinador(val ruta: String) {
    object Inicio : RutaCoordinador("inicio")
    object Estancias : RutaCoordinador("estancias")
    object Reportes : RutaCoordinador("reportes")
    object Perfil : RutaCoordinador("perfil")
}