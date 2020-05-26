package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array
import java.util.*

data class MeliSearchResult (
    @SerializedName("id") val id : String,
    @SerializedName("site_id") val site_id : String,
    @SerializedName("title") val title : String,
    @SerializedName("seller") val seller : Seller,
    @SerializedName("price") val offset : Double,
    @SerializedName("currency_id") val currencyId : String,
    @SerializedName("available_quantity") val availableQuantity : Int,
    @SerializedName("sold_quantity") val soldQuantity : Int,
    @SerializedName("buying_mode") val buyingMode : String,
    @SerializedName("listing_type_id") val listingTypeId : String,
    @SerializedName("stop_time") val stopTime : Date,
    @SerializedName("condition") val condition : String,
    @SerializedName("permalink") val permalink : String,
    @SerializedName("thumbnail") val thumbnail : String,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago : Boolean,
    @SerializedName("address") val address : Address,
    //@SerializedName("attributes") val attributes : Array,
    @SerializedName("category_id") val categoryId : String
    //@SerializedName("tags") val tags : Array
    )