package com.janettha.navigationdrawerexample

import android.app.Application
import com.janettha.navigationdrawerexample.sys.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@MainApplication)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(
                listOf(
                    ProvidersModules.module,
                    ViewModelModules.module,
                    RepositoryModules.module,
                    NetworkModules.modules,
                    DataSourceModules.modules,
                    IteratorModules.modules,
                    UseCasesModules.modules
                )
            )
        }
    }
}