package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.services.PeopleService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { PeopleService(get()) }

}
