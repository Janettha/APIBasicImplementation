package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemote
import org.koin.dsl.module

class DataSourceModules {

    companion object {

        val modules = module {

            single { PokemonRemote(get()) }

        }

    }

}