package com.semirsuljevic.raiffaisenmobileapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    val api: RMBAPI by lazy {
        Retrofit.Builder()
            .baseUrl("http://rmbcloneapi.azurewebsites.net/api/")
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RMBAPI::class.java)
    }
}