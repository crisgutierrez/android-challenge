package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.services.PeopleService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { PeopleService(get()) }

}
