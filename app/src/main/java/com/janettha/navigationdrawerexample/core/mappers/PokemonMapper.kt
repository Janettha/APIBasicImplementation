package com.janettha.navigationdrawerexample.core.mappers

import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.ItemPokemon

class PokemonMapper {

    companion object {

        @JvmStatic
        fun GetPokemonListDtoResponse.toPokemonEntity(): PokemonEntity {
            return PokemonEntity(
                this.next,
                this.previous,
                results.map {
                    ItemPokemon(
                        it.name,
                        it.url
                    )
                }
            )
        }

    }

}