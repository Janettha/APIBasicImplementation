package com.janettha.navigationdrawerexample.sys.util.common

import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse

/**
 * Enum for UI status and propagate from data to presentation
 */
sealed class NetworkState<T> {

    /**
     * Propagate loading status
     */
    class Loading<T> : NetworkState<T>()

    /**
     * Propagate success data loading
     */
    data class Loaded<T>(val data: GetPokemonListDtoResponse?) : NetworkState<T>()

    /**
     * Propagate empty status
     */
    class Empty<T> : NetworkState<T>()

    /**
     * Propagate error message
     */
    data class Error<T>(val error: String) : NetworkState<T>()

}