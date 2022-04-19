package com.janettha.navigationdrawerexample.core.util

import androidx.annotation.StringRes
import com.janettha.navigationdrawerexample.R

sealed class TextResource {

    data class String(val text: kotlin.String) : TextResource()

    data class Resource(@StringRes val res: Int) : TextResource()

    companion object {

        fun unknownError(): TextResource = Resource(R.string.error_unknown)

    }

}