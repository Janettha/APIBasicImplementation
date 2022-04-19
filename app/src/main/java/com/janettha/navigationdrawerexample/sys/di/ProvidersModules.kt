package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.sys.util.providers.AppExecutors
import com.janettha.navigationdrawerexample.sys.util.providers.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class ProvidersModules {

    companion object {

        val module = module {

            single { ResourceProvider(androidContext()) }

            single { AppExecutors() }

        }

    }

}