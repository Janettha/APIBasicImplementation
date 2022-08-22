package com.janettha.navigationdrawerexample.domain.model

data class PokemonResult(
    val name: String,
    val url: String
)

fun List<PokemonResult>.toPokemonListResult(): List<PokemonResult>{
    return map { pokemon ->
        PokemonResult(
            pokemon.name,
            pokemon.url
        )
    }
}