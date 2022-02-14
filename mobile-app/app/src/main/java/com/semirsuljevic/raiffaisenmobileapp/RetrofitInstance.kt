package com.semirsuljevic.raiffaisenmobileapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api: RMBAPI by lazy {
        Retrofit.Builder()
            .baseUrl("http://rmbcloneapi.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RMBAPI::class.java)
    }
}