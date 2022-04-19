package com.janettha.navigationdrawerexample.data.datasources.web

import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.*
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNoInternetException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNullBodyException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiServerException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRemote (
    private val webService: WebService
) {
    fun downloadPokemonList(
        request: PokemonDetailsRequest
    ): Flow<Resource<GetPokemonListDtoResponse>> {
        val params = HashMap<String, Any>()
        params.addToBody(request)

        return webService.getPokemonList(params).map { response ->
            try {
                Resource.Success(response.data)
            } catch (_: ApiNoInternetException) {
                Resource.Error(TextResource.Resource(R.string.error_could_not_reach_server))
            } catch (_: ApiServerException) {
                Resource.Error(TextResource.Resource(R.string.error_could_not_reach_server))
            } catch (_: ApiNullBodyException) {
                Resource.Error(TextResource.Resource(R.string.error_something_went_wrong))
            }
        }
    }
}