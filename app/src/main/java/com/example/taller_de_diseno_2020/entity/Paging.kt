package com.example.taller_de_diseno_2020.entity

import com.google.gson.annotations.SerializedName

data class Paging (
    @SerializedName("total") val total : Int,
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("primary_result") val primaryResult : Int
    )