package com.semirsuljevic.raiffaisenmobileapp

import com.semirsuljevic.raiffaisenmobileapp.models.FAQItem
import com.semirsuljevic.raiffaisenmobileapp.models.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoApi {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("Faq")
    suspend fun getFAQ(): Response<List<FAQItem>>
    @GET("Location")
    suspend fun getLocations(): Response<List<Location>>

    @POST(value = "token")
    @Headers("Content-Type: text/plain; charset=UTF-8")
    suspend fun getToken(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<String>

}


data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)