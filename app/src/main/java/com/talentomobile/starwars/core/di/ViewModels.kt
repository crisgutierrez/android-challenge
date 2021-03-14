package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.view.viewmodels.PeopleViewModel
import com.talentomobile.starwars.features.people.view.viewmodels.PersonDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PeopleViewModel(get()) }
    viewModel { PersonDetailViewModel(get(), get()) }

}
