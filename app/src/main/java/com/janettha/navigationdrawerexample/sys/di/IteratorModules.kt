package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.domain.use_cases.HomeUseCases
import org.koin.dsl.module

class IteratorModules {

    companion object {

        val modules = module {

            single { HomeUseCases(get()) }

        }

    }

}