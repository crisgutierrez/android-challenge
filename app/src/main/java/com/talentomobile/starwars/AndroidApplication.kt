package com.talentomobile.starwars

import android.app.Application
import com.talentomobile.starwars.core.di.applicationModule
import com.talentomobile.starwars.core.di.databaseModule
import com.talentomobile.starwars.core.di.networkModule
import com.talentomobile.starwars.core.di.dataSourceModule
import com.talentomobile.starwars.core.di.repositoryModule
import com.talentomobile.starwars.core.di.viewModelModule
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
                repositoryModule,
                dataSourceModule,
                databaseModule
            ))
        }
    }
}
