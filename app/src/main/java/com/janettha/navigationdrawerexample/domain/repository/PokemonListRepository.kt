package com.janettha.navigationdrawerexample.domain.repository

import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {

    suspend fun getPokemonList(
        offset: Int?,
        limit: Int?
    ): Flow<Resource<PokemonEntity>>

}