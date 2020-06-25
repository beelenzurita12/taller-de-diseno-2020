package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName

data class SearchResult (
    @SerializedName("site_id") val siteId:String,
    @SerializedName("query") val query:String,
    @SerializedName("results") val results:ArrayList<MeliSearchResult>
)