package com.janettha.navigationdrawerexample.data.datasources.web.dto.response


import com.google.gson.annotations.SerializedName

data class GetPokemonListDtoResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<ItemPokemon>
)
data class ItemPokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)