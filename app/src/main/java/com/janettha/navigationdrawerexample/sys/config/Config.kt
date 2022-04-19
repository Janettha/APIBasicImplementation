package com.janettha.navigationdrawerexample.sys.config

import com.janettha.navigationdrawerexample.BuildConfig
import com.janettha.navigationdrawerexample.sys.util.permission.AppPermission

interface Constants {

    class Permission {

        companion object {

            const val LOCATION_REQUEST_CODE = 1010

            const val LOCATION_BACKGROUND_REQUEST_CODE = 56

            val LOCATION_PERMISSIONS =
                listOf(
                    AppPermission.AccessFineLocation.androidPermission,
                    AppPermission.AccessCoarseLocation.androidPermission,
                    AppPermission.AccessBackgroundLocation.androidPermission,
                )
        }

    }

    /**
     * Web consumer constants
     */
    class Web {

        companion object {
            /**
             * Client max connect timeout
             */
            const val CONNECT_TIMEOUT = 10L

            /**
             * Client max write timeout
             */
            const val WRITE_TIMEOUT = 30L

            /**
             * Client max read timeout
             */
            const val READ_TIMEOUT = 10L

            /**
             * Web API base URL
             */
            const val API_BASE_URL = BuildConfig.WS_SERVICE

            /**
             * GOOGLE PLACE API KEY
             */
            //const val PLACES_API_KEY = "AIzaSyDi7MTsCPLnEp5Ho0DRME1KEw48l51d8Bk"

        }

    }
}