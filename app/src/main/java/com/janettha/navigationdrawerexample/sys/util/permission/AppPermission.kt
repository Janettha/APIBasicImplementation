package com.janettha.navigationdrawerexample.sys.util.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Contract for all permissions needed in the app, single permissions list here
 */
enum class AppPermission(val androidPermission: String) {

    AccessFineLocation(Manifest.permission.ACCESS_FINE_LOCATION),

    AccessCoarseLocation(Manifest.permission.ACCESS_COARSE_LOCATION),

    AccessBackgroundLocation(Manifest.permission.ACCESS_BACKGROUND_LOCATION);

    companion object {

        @JvmStatic
        fun fromStringPermission(androidPermission: String): AppPermission {

            values().iterator().forEach { appPermission ->
                if (appPermission.androidPermission == androidPermission)
                    return appPermission
            }

            throw RuntimeException("Android permission not declared in ${AppPermission::class.simpleName}: $androidPermission")
        }

    }

}