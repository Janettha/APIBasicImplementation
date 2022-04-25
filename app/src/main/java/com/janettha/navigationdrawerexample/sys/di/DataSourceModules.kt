package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemote
import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemoteRequest
import org.koin.dsl.module

class DataSourceModules {

    companion object {

        val modules = module {

            single { PokemonRemote(get()) }

            single { PokemonRemoteRequest(get()) }
        }

    }

}