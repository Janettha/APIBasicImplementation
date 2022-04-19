package com.janettha.navigationdrawerexample.core.data

import com.janettha.navigationdrawerexample.core.util.TextResource

sealed class Resource<T>(val data: T? = null, val error: TextResource? = null) {

    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(error: TextResource, data: T? = null) : Resource<T>(data, error)

}