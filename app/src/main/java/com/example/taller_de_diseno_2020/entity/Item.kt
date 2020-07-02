package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Double,
    @SerializedName("thumbnail") val thumbnail : String,
    @SerializedName("sold_quantity") val soldQuantity : Int,
    @SerializedName("condition") val condition : String
)