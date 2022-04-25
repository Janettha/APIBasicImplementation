package com.janettha.navigationdrawerexample.data

import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.ItemPokemon

class PokemonEntity(
    val next: String?,
    val previous: String?,
    val results: List<ItemPokemon>
) {

    fun countResults(): Int {
        return results.size
    }

}