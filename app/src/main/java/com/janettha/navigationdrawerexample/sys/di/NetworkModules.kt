package com.janettha.navigationdrawerexample.sys.di

import android.provider.SyncStateContract
import com.google.gson.GsonBuilder
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.sys.config.Constants
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.FlowResponseCallAdapterFactory
import com.janettha.navigationdrawerexample.sys.framework.retrofit_flow_adapter.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModules {

    companion object {

        val modules = module {

            //factory { provideLoggingInterceptor() }

            //factory { AuthInterceptor(get()) }

            factory { provideHttpClient(get()) }

            factory { provideRetrofit(get()) }

            single { provideWebService(get()) }

        }

        private fun provideHttpClient(
            logInterceptor: HttpLoggingInterceptor
            //, authInterceptor: AuthInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(Constants.Web.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.Web.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.Web.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                //.addInterceptor(authInterceptor)
                .build()
        }

        private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.Web.API_BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    )
                )
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addCallAdapterFactory(FlowResponseCallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

        private fun provideWebService(retrofit: Retrofit): WebService =
            retrofit.create(WebService::class.java)

    }
}