package com.janettha.navigationdrawerexample.data.repository

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.mappers.PokemonMapper.Companion.toPokemonEntity
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemote
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerEmpty
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerError
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerSuccess
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.domain.repository.PokemonListRepository
import com.janettha.navigationdrawerexample.sys.util.common.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class PokemonListRepositoryImpl(
    private val remote: PokemonRemote
): PokemonListRepository {

    override suspend fun getPokemonListFlow(
        offset: Int?,
        limit: Int?
    ): Flow<Resource<PokemonEntity>> {
        Log.d("Pokemon", "PokemonListRepositoryImpl, getPokemonList")
        return remote.downloadPokemonListFlow(PokemonDetailsRequest(offset, limit)).map { response ->
            Log.d("Pokemon", "PokemonListRepositoryImpl, getPokemonList success")
            if(response.data?.results!!.isEmpty()){
                Resource.Error<PokemonEntity>(TextResource.String("Empty data"))
            } else {
                Resource.Success(response.data?.toPokemonEntity())
            }
        }.catch {
            Log.d("Pokemon", "PokemonListRepositoryImpl, getPokemonList error")
            Resource.Error<PokemonEntity>(TextResource.Resource(R.string.error_unknown))
        }
    }

}