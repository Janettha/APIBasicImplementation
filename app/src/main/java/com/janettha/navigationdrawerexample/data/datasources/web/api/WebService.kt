package com.janettha.navigationdrawerexample.data.datasources.web.api

import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.data.datasources.web.responses.ApiResponse
import com.janettha.navigationdrawerexample.data.datasources.web.responses.OnGetPokemonResponse
import retrofit2.http.*

@JvmSuppressWildcards
interface WebService {

    @GET("pokemon")
    suspend fun getPokemonListA(@QueryMap body: Map<String, Any>): Request

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Request

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    //): ApiResponse<OnGetPokemonResponse>
    ) : Request

}