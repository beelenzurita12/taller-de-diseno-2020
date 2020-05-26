package com.example.taller_de_diseno_2020.api

import com.example.taller_de_diseno_2020.entity.SearchResult
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val argentina = "MLA"

    private fun getApi(): MeliApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/$argentina/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(MeliApi::class.java)
    }

    fun search(q: String, callback: Callback<SearchResult>) {
        getApi().search(q.replace(" ", "+")).enqueue(callback)
    }
}