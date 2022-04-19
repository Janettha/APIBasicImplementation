package com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter

import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowResponseCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Flow::class.java) {
            return null
        }

        check(returnType is ParameterizedType) { "Flow return type must be parameterized as Flow<Foo> or Flow<out Foo>" }

        val responseType = getParameterUpperBound(0, returnType)

        return FlowResponseCallAdapter<Any>(responseType)
    }

    companion object {
        @JvmStatic
        fun create() = FlowResponseCallAdapterFactory()
    }
}