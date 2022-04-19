package com.janettha.navigationdrawerexample.data.repository

import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.mappers.PokemonMapper.Companion.toPokemonEntity
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemote
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.domain.repository.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class PokemonListRepositoryImpl(
    private val remote: PokemonRemote
): PokemonListRepository {

    override suspend fun getPokemonList(
        offset: Int?,
        limit: Int?
    ): Flow<Resource<PokemonEntity>> {
        return remote.downloadPokemonList(PokemonDetailsRequest(offset, limit)).map { response ->
            Resource.Success(response.data?.toPokemonEntity())
        }.catch {
            Resource.Error<PokemonEntity>(TextResource.Resource(R.string.error_unknown))
        }
    }

}