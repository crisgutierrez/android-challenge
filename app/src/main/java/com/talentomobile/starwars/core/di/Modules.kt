package com.talentomobile.starwars.core.di

import android.content.Context
import android.content.SharedPreferences
import com.talentomobile.skell.BuildConfig
import com.talentomobile.starwars.core.platform.ContextHandler
import com.talentomobile.starwars.core.platform.NetworkHandler
import com.talentomobile.starwars.features.people.view.adapters.PeopleAdapter
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { ContextHandler(get()) }
    factory { NetworkHandler(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ENVIRONMENT_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Si necesitamos el builder para proporcionarle otra urlbase
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }

    factory {
        Dispatchers.IO
    }
}
val applicationModule = module(override = true) {

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            BuildConfig.APPLICATION_ID + "-" + BuildConfig.ENVIRONMENT,
            Context.MODE_PRIVATE
        )
    }

    factory { PeopleAdapter() }
}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}
