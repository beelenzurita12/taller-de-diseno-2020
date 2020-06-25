package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Int,
    @SerializedName("thumbnail") val thumbnail : String
)