package com.janettha.navigationdrawerexample.data.datasources.web

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerEmpty
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerError
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerSuccess
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.*
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNoInternetException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNullBodyException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiServerException
import com.janettha.navigationdrawerexample.sys.util.common.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class PokemonRemote (
    private val webService: WebService
) {

    fun downloadPokemonListFlow(
        request: PokemonDetailsRequest
    ): Flow<Resource<GetPokemonListDtoResponse>> {
        val params = HashMap<String, Any>()
        params.addToBody(request)

        Log.d("Pokemon","PokemonRemote, downloadPokemonList")
        return webService.getPokemonListFlow(params).map { response ->
            Log.d("Pokemon","PokemonRemote, downloadPokemonList, success")
            Resource.Success(response.data)
        }.catch {
            Log.d("Pokemon","PokemonRemote, downloadPokemonList, error")
            when(it) {
                is ApiNoInternetException -> Resource.Error<TextResource.Resource>(
                    TextResource.Resource(R.string.error_could_not_reach_server)
                )
                is ApiServerException -> Resource.Error<TextResource.Resource>(
                    TextResource.Resource(R.string.error_could_not_reach_server)
                )
                is ApiNullBodyException -> Resource.Error<TextResource.Resource>(
                    TextResource.Resource(R.string.error_something_went_wrong)
                )
            }
        }
    }

}