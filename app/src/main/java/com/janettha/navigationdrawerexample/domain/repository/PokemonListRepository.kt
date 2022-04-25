package com.janettha.navigationdrawerexample.domain.repository

import androidx.lifecycle.MediatorLiveData
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.PokemonEntity
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.sys.util.common.NetworkState
import kotlinx.coroutines.flow.Flow

interface PokemonListRepository {

    suspend fun getPokemonListFlow(
        offset: Int?,
        limit: Int?
    ): Flow<Resource<PokemonEntity>>

}