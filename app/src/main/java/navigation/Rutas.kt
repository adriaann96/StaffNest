package com.example.staffnest.navigation

sealed class Ruta(val ruta: String) {
    object Login : Ruta("login")
    object Presidencia : Ruta("presidencia")
    object Responsable : Ruta("responsable")
    object Coordinador : Ruta("coordinador")
    object Empleado : Ruta("empleado")
}

sealed class RutaPresidencia(val ruta: String) {
    object Estancias :
        RutaPresidencia("presidencia_estancias")
    object Estadisticas :
        RutaPresidencia("presidencia_estadisticas")
    object Reportes :
        RutaPresidencia("presidencia_reportes")
    object Personal :
        RutaPresidencia("presidencia_personal")
    object DetalleEstancia :
        RutaPresidencia("presidencia_detalle_estancia")
    object DetalleSala :
        RutaPresidencia("presidencia_detalle_sala")
}

sealed class RutaEmpleado(val ruta: String) {
    object Registro : RutaEmpleado("registro")
    object Horario : RutaEmpleado("horario")
    object Estadisticas : RutaEmpleado("estadisticas")
    object Solicitudes : RutaEmpleado("solicitudes")
    object Historial : RutaEmpleado("empleado_historial")
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