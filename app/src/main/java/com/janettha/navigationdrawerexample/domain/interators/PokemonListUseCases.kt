package com.janettha.navigationdrawerexample.domain.interators

import com.janettha.navigationdrawerexample.domain.repositories.PokemonListRepository

class PokemonListUseCases(
    private val repository: PokemonListRepository
) {

    /**
     * Get pokemon list by offset and limit index
     */
    fun downloadPokemonList(offset: Int, limit: Int) = repository.downloadPokemonList(offset, limit)


}