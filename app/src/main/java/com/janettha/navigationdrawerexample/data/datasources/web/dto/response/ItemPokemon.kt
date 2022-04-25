package com.janettha.navigationdrawerexample.data.datasources.web.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemPokemon(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("url")
    val url: String
)