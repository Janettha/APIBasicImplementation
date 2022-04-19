package com.janettha.navigationdrawerexample.core.mappers

import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse

class PokemonMapper {

    companion object {

        @JvmStatic
        fun GetPokemonListDtoResponse.toPokemonEntity(): PokemonEntity {
            return PokemonEntity(
                this.next,
                this.previous,
                results!!.map {
                    GetPokemonListDtoResponse.ItemPokemon(
                        it.name,
                        it.url
                    )
                }
            )
        }

    }

}