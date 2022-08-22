package com.janettha.navigationdrawerexample.ui.home

sealed class PokemonHomeFragmentEvent {

    data class OnClickPokemon(val url: String) : PokemonHomeFragmentEvent()

}