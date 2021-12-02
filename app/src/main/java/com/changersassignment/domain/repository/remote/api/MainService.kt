package com.enfecassignment.domain.repository.remote.api

import com.changersassignment.domain.model.BreedData
import com.enfecassignment.domain.repository.remote.APIService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*


interface MainService : APIService {
    @Headers("Content-Type: application/json")
    @GET("breeds?")
    fun getBreeds(
        @Query("page") page: Int, @Query("limit") limit: Int,
    ): Call<ArrayList<BreedData>?>?

    @Headers("Content-Type: application/json")
    @GET("breeds/search?")
    fun getSpecificBreed(@Query("q") breedName: String): Call<ArrayList<BreedData>?>?
}
