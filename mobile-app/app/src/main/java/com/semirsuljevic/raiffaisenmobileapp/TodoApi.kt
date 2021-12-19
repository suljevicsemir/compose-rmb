package com.semirsuljevic.raiffaisenmobileapp

import com.semirsuljevic.raiffaisenmobileapp.models.FAQItem
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("Faq")
    suspend fun getFAQ(): Response<List<FAQItem>>


}


data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)