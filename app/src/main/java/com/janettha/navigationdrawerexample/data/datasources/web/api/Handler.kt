package com.janettha.navigationdrawerexample.data.datasources.web.api

import com.janettha.navigationdrawerexample.data.datasources.web.responses.ApiResponse
import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
sealed class Handler<T> {

    companion object {

        fun <T> create(error: Throwable): HandlerError<T> =
            HandlerError(error.message ?: "unknown error")

        fun <T> create(response: Response<T>): Handler<T> {
            return if (response.isSuccessful) {
                val bodyResponse = response.body() as? ApiResponse<*>
                    ?: return HandlerError("Response not subtype of ${ApiResponse::class.java.canonicalName}")

                val body = response.body()

                if (bodyResponse.code != 200)
                    return HandlerError(bodyResponse.err ?: "unknown server error")

                if (body == null || response.code() == 204)
                    HandlerEmpty()
                else
                    HandlerSuccess(body)
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) response.message() else msg
                HandlerError(errorMsg ?: "unknown error")
            }
        }
    }

}

/**
 * Request result OK
 */
data class HandlerSuccess<T>(
    val body: T
) : Handler<T>()

/**
 * separate class for HTTP 204 resposes so that we can make ApiSuccessResponse's body non-null.
 */
class HandlerEmpty<T> : Handler<T>()

/**
 * Any error by web request in terms of String message
 */
data class HandlerError<T>(val errorMessage: String) : Handler<T>()