package com.janettha.navigationdrawerexample.domain.repositories

import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemoteRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest

class PokemonListRepository(
    private val pokemonRemoteRequest: PokemonRemoteRequest
) {

    /**
     * Request pokemon list by offset and limit from Remote
     */
    fun downloadPokemonList(offset: Int, limit: Int)
    = pokemonRemoteRequest.downloadPokemonList(PokemonDetailsRequest(offset, limit))

}