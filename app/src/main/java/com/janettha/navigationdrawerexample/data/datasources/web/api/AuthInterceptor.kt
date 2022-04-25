package com.janettha.navigationdrawerexample.data.datasources.web.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // access to request
        val requestBuilder = chain.request().newBuilder()

        // build request
        return chain.proceed(requestBuilder.build())
    }

}