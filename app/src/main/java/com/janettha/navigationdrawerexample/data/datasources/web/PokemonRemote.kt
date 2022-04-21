package com.janettha.navigationdrawerexample.data.datasources.web

import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.*
import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.data.datasources.web.responses.ApiResponse
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNoInternetException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiNullBodyException
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.ApiServerException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PokemonRemote (
    private val webService: WebService
) {
    suspend fun downloadPokemonList(
        request: PokemonDetailsRequest
    ): Flow<Resource<GetPokemonListDtoResponse>> {
        val params = HashMap<String, Any>()
        params.addToBody(request)

        val response = webService.getPokemonListA(params)

        if (response.count > 1){
            println("ME ABURRo")
        }

        return flow {
            GetPokemonListDtoResponse(1,"2","1", listOf(ItemPokemon("name","url")))
        }
    }
}