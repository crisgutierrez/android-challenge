package com.talentomobile.starwars

import android.app.Application
import com.talentomobile.starwars.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                applicationModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataSourceModule,
                databaseModule
            ))
        }
    }
}
