package com.janettha.navigationdrawerexample.data.datasources.web.api

import androidx.lifecycle.LiveData
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.data.datasources.web.responses.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

@JvmSuppressWildcards
interface WebService {

    // region FILTERS AND VIBES

    @GET("pokemon/")
    fun getPokemonList(@QueryMap body: Map<String, Any>): Flow<ApiResponse<GetPokemonListDtoResponse>>

    // endregion

}