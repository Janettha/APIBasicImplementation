package com.janettha.navigationdrawerexample.data.datasources.web.responses

import com.google.gson.annotations.Expose

open class ApiResponse<T>(
    @Expose
    val code: Int,
    @Expose
    val err: String?,
    @Expose
    val data: T?
) {

    constructor() : this(0, null, null)

    fun isSuccessful() = code == 200

}