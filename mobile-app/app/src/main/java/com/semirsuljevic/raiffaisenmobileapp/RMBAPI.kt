package com.semirsuljevic.raiffaisenmobileapp

import com.semirsuljevic.raiffaisenmobileapp.models.City
import com.semirsuljevic.raiffaisenmobileapp.models.FAQItem
import com.semirsuljevic.raiffaisenmobileapp.models.Location
import com.semirsuljevic.raiffaisenmobileapp.models.locations.ATMFilter
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchServiceType
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BranchType
import com.semirsuljevic.raiffaisenmobileapp.models.user.LoginCredentials
import com.semirsuljevic.raiffaisenmobileapp.models.user.TokenPair
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RMBAPI {
    @GET("Faq")
    suspend fun getFAQ(): Response<List<FAQItem>>
    @GET("Location")
    suspend fun getLocations(): Response<List<Location>>

    @POST(value = "Token")
    suspend fun getTokenPair(
        @Body loginCredentials: LoginCredentials
    ): Response<TokenPair>

    @GET(value = "City")
    suspend fun getCities(): Response<List<City>>

    @GET(value = "BranchServiceType")
    suspend fun getBranchServiceTypes(): Response<List<BranchServiceType>>

    @GET(value = "BranchType")
    suspend fun getBranchTypes() : Response<List<BranchType>>

    @GET(value = "ATMFilter")
    suspend fun getATMFilters() : Response<List<ATMFilter>>



}

