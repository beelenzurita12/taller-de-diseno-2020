package com.example.taller_de_diseno_2020.api
import com.example.taller_de_diseno_2020.entity.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliApi {

    @GET("search")
    fun search(@Query("q") q: String): Call<SearchResult>
}