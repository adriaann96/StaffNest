package com.example.staffnest.network

import com.example.staffnest.model.InicioSesion
import com.example.staffnest.model.TokenSesion
import com.example.staffnest.model.UsuarioSesion
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @GET("api/v1/ping")
    suspend fun ping(): Response<ResponseBody>

    @POST("api/v1/sesiones")
    suspend fun iniciarSesion(
        @Body datos: InicioSesion
    ): Response<TokenSesion>

    @GET("api/v1/sesiones/actual")
    suspend fun obtenerSesion(
        @Header("Authorization") token: String
    ): Response<UsuarioSesion>

    @DELETE("api/v1/sesiones/actual")
    suspend fun cerrarSesion(
        @Header("Authorization") token: String
    ): Response<Unit>

}