package com.janettha.navigationdrawerexample.data.datasources.web.responses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.janettha.navigationdrawerexample.domain.model.Pokemon
import com.janettha.navigationdrawerexample.domain.model.PokemonResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnGetPokemonResponse(
    @Expose
    val count: Int?,
    @Expose
    val next: String? = "",
    @Expose
    val previous: String? = "",
    @Expose
    val results: List<ItemPokemon>?
) : Parcelable {

    @Parcelize
    data class ItemPokemon(
        @Expose
        val name: String?,
        @Expose
        val url: String?
    ) : Parcelable

}

fun OnGetPokemonResponse.toPokemonListResponse(
): Pokemon {
    return Pokemon(
        this.results?.map { it -> PokemonResult(it.name!!, it.url!!) }
    )
}