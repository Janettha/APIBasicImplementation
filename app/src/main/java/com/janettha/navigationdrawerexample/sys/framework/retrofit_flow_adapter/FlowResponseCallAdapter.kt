package com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter

import com.janettha.navigationdrawerexample.data.datasources.web.responses.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FlowResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<T>> {

    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            if (t.cause is SocketTimeoutException) {
                                continuation.resumeWithException(
                                    ApiNoInternetException(
                                        t.message, t
                                    )
                                )
                            } else {
                                continuation.resumeWithException(t)
                            }
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            try {
                                val gripBody = response.body() as? ApiResponse<*>

                                if (gripBody == null)
                                    continuation.resumeWithException(ApiNullBodyException())

                                if (response.isSuccessful.not() ||
                                    gripBody?.isSuccessful()?.not() == true
                                )
                                    continuation.resumeWithException(ApiServerException())

                                continuation.resume(response.body()!!)
                            } catch (e: Exception) {
                                continuation.resumeWithException(e)
                            }
                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType

}

class ApiNullBodyException(message: String? = null, throwable: Throwable? = null) :
    Exception(message, throwable)

class ApiServerException(message: String? = null, throwable: Throwable? = null) :
    Exception(message, throwable)

class ApiNoInternetException(message: String? = null, throwable: Throwable? = null) :
    Exception(message, throwable)