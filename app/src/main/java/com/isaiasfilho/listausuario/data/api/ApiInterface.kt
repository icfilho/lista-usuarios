package com.isaiasfilho.listausuario.data.api


import com.isaiasfilho.listausuario.data.api.model.ApiResponse
import retrofit2.http.GET


interface ApiInterface {
    @GET("users?per_page=12")
    suspend fun getUsers() : ApiResponse
}