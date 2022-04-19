package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.data.repository.PokemonListRepositoryImpl
import com.janettha.navigationdrawerexample.domain.repository.PokemonListRepository
import org.koin.dsl.module

class RepositoryModules {

    companion object {

        val module = module {
            single{ PokemonListRepositoryImpl(get()) as PokemonListRepository }
        }
    }

}