package com.example.weathernow

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("data/{id}/{type}")
    fun getdata(@Path("id") id:Double,@Path("type") type:String,@Query("latitude") latitude:String?,@Query("longitude") longitude:String?,@Query("apiId") apiId:String?): Call<List<Components>>

}