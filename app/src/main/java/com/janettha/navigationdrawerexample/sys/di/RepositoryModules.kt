package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.data.repository.PokemonRepositoryImpl
import com.janettha.navigationdrawerexample.domain.repository.PokemonRepository
import org.koin.dsl.module

class RepositoryModules {

    companion object {

        val module = module {

            //single{ PokemonListRepositoryImpl(get()) as PokemonListRepository }

            single { PokemonRepositoryImpl(get()) as PokemonRepository }

        }
    }

}