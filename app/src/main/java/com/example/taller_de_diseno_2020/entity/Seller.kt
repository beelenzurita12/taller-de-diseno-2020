package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array

data class Seller (
    @SerializedName("id") val id : Int,
    @SerializedName("power_seller_status") val powerSellerStatus : String,
    @SerializedName("car_dealer") val carDealer : Boolean,
    @SerializedName("real_estate_agency") val realStateAgency : Boolean
    //@SerializedName("tags") val tags : Array
    )