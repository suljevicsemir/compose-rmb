package com.semirsuljevic.raiffaisenmobileapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitInstance {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    val api: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://rmbcloneapi.azurewebsites.net/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}