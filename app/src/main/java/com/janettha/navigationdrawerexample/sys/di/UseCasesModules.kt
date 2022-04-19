package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.domain.use_cases.use_case.PokemonListUseCase
import org.koin.dsl.module

class UseCasesModules {

    companion object {

        val modules = module {

            single { PokemonListUseCase(get()) }

        }

    }

}