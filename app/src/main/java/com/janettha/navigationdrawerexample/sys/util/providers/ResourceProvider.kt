package com.janettha.navigationdrawerexample.sys.util.providers

import android.content.Context

class ResourceProvider(
    private val context: Context
) {

    fun getString(id: Int) : String {
        return context.getString(id)
    }

}