package com.janettha.navigationdrawerexample.data.datasources.web

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerEmpty
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerError
import com.janettha.navigationdrawerexample.data.datasources.web.api.HandlerSuccess
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.sys.util.common.NetworkState
import androidx.lifecycle.LiveData
import com.janettha.navigationdrawerexample.sys.util.reactive.BackgroundMediatorLiveData

class PokemonRemoteRequest (
    private val webService: WebService
) {

    fun downloadPokemonList(
        request: PokemonDetailsRequest
    ): LiveData<NetworkState<GetPokemonListDtoResponse>> {

        var mediatorPokemonList: MediatorLiveData<NetworkState<GetPokemonListDtoResponse>>
        = MediatorLiveData()
        mediatorPokemonList.postValue(NetworkState.Loading())

        //val params = HashMap<String, Any>()
        //params.addToBody(request)

        Log.d("Pokemon","PokemonRemote, downloadPokemonList")
        //val call = webService.getPokemonList(params)
        val call = webService.getPokemonList(HashMap<String, Any>().apply {
            request.offset?.let { put("offset", it) }
            request.limit?.let { put("limit", it) }
        })
        mediatorPokemonList.addSource(call) {
            mediatorPokemonList.removeSource(call)
            when(it) {
                is HandlerSuccess -> {
                    if (it.body.data != null) {
                        mediatorPokemonList.postValue(NetworkState.Loaded(it.body.data))
                    } else
                        mediatorPokemonList.postValue(NetworkState.Empty())
                }
                is HandlerEmpty -> mediatorPokemonList.postValue(NetworkState.Empty())
                is HandlerError -> mediatorPokemonList.postValue(NetworkState.Error(it.errorMessage))
            }

        }

        return mediatorPokemonList
    }

}