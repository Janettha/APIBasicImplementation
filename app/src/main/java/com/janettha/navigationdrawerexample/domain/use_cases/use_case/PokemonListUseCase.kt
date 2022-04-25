package com.janettha.navigationdrawerexample.domain.use_cases.use_case

import android.util.Log
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.domain.repository.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonListUseCase(
    private val repository: PokemonListRepository
) {

    suspend operator fun invoke(offset: Int?, limit: Int?): Flow<Resource<PokemonEntity>> {
        Log.d("Pokemon", "PokemonListUseCase, invoke: getPokemonList")
        return when (val response = repository.getPokemonListFlow(offset, limit)) {
            is Resource.Success<*> -> {
                flow { Resource.Success(response.data) }
            }
            is Resource.Error<*> -> {
                flow { Resource.Error<PokemonEntity>(response.error!!) }
            }
            else -> {
                flow { Resource.Error<String>(TextResource.unknownError()) }
            }
        }
    }

}