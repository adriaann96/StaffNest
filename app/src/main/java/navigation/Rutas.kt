package com.example.staffnest.navigation

sealed class Ruta(val ruta: String) {
    object Login : Ruta("login")
    object Presidencia : Ruta("presidencia")
    object Responsable : Ruta("responsable")
    object Asistente : Ruta("asistente")
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
    object Personal : RutaResponsable("personal")
    object Horarios : RutaResponsable("horarios")
    object Incidencias : RutaResponsable("incidencias")
    object Asistencia : RutaResponsable("responsable_asistencia")
    object DetalleSala : RutaResponsable("responsable_detalle_sala/{salaId}")
}

sealed class RutaAsistente(val ruta: String) {
    object Inicio : RutaAsistente("asistente_inicio")
    object Personal : RutaAsistente("asistente_personal")
    object Horarios : RutaAsistente("asistente_horarios")
    object Incidencias : RutaAsistente("asistente_incidencias")
    object Asistencia : RutaAsistente("asistente_asistencia")
    object DetalleSala : RutaAsistente("asistente_detalle_sala/{salaId}")
}