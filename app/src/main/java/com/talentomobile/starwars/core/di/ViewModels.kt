package com.talentomobile.starwars.core.di

import com.talentomobile.starwars.features.people.view.viewmodels.PeopleViewModel

val viewModelModule = module {

    viewModel { PeopleViewModel(get()) }

}
