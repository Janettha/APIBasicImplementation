package com.janettha.navigationdrawerexample.data

import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse

class PokemonEntity(
    val next: String?,
    val previous: String?,
    val results: List<GetPokemonListDtoResponse.ItemPokemon>
) {

    fun countResults(): Int {
        return results.size
    }

}