package com.talentomobile.starwars.core.di

import androidx.room.Room
import com.talentomobile.skell.BuildConfig
import com.talentomobile.starwars.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        val dbBuilder = Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            BuildConfig.APPLICATION_ID
        )
        dbBuilder.build()
    }

    // DAOs
    single { get<AppDatabase>().favoritePeopleDao() }
}
