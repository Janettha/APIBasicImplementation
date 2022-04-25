package com.janettha.navigationdrawerexample.data.datasources.web.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPokemonListDtoResponse(
    @Expose
    @SerializedName("count")
    val count: Int?,
    @Expose
    @SerializedName("next")
    val next: String?,
    @Expose
    @SerializedName("previous")
    val previous: String?,
    @Expose
    @SerializedName("results")
    val results: List<ItemPokemon>?
)