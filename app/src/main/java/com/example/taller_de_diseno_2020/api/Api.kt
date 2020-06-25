package com.example.taller_de_diseno_2020.api

import com.example.taller_de_diseno_2020.entity.Item
import com.example.taller_de_diseno_2020.entity.ItemDescription
import com.example.taller_de_diseno_2020.entity.SearchResult
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {


    private fun getApi(): MeliApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(MeliApi::class.java)
    }

    fun search(q: String, callback: Callback<SearchResult>) {
        getApi().search(q.replace(" ", "+")).enqueue(callback)
    }

    fun getItemById(itemId: String, callback: Callback<Item>) {
        getApi().getItemById(itemId).enqueue(callback)
    }

    fun getItemDescription(itemId: String, callback: Callback<ArrayList<ItemDescription>>) {
        getApi().getItemDescription(itemId).enqueue(callback)
    }
}