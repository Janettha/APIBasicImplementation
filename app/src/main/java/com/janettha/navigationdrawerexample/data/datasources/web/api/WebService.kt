package com.janettha.navigationdrawerexample.data.datasources.web.api

import android.os.Parcelable
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.data.datasources.web.responses.*
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize
import retrofit2.http.*

@JvmSuppressWildcards
interface WebService {

    @GET("pokemon")
    suspend fun getPokemonListA(@QueryMap body: Map<String, Any>): Request

}