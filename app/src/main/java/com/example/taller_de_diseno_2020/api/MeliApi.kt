package com.example.taller_de_diseno_2020.api
import com.example.taller_de_diseno_2020.entity.Item
import com.example.taller_de_diseno_2020.entity.ItemDescription
import com.example.taller_de_diseno_2020.entity.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliApi {

    @GET("/sites/MLA/search")
    fun search(@Query("q") q: String): Call<SearchResult>

    @GET("items/{itemId}/")
    fun getItemById(@Path("itemId") id: String): Call<Item>

    @GET("items/{itemId}/descriptions")
    fun getItemDescription(@Path("itemId") id: String): Call<ArrayList<ItemDescription>>
}