package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array
import java.util.*

data class MeliSearchResult (
    @SerializedName("id") val id : String,
    @SerializedName("site_id") val site_id : String,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Double,
    @SerializedName("available_quantity") val availableQuantity : Int,
    @SerializedName("sold_quantity") val soldQuantity : Int,
    @SerializedName("condition") val condition : String,
    @SerializedName("permalink") val permalink : String,
    @SerializedName("thumbnail") val thumbnail : String
    )