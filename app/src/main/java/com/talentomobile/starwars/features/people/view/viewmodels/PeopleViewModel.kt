package com.talentomobile.starwars.features.people.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.talentomobile.starwars.core.extensions.cancelIfActive
import com.talentomobile.starwars.core.interactor.UseCase
import com.talentomobile.starwars.core.functional.Error
import com.talentomobile.starwars.core.functional.Success
import com.talentomobile.starwars.core.platform.BaseViewModel
import com.talentomobile.starwars.features.people.models.view.PeopleView
import com.talentomobile.starwars.features.people.usecases.GetPeople
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val getPeople: GetPeople
) : BaseViewModel() {

    var people = MutableLiveData<PeopleView>()
    var getMoviesJob: Job? = null

    fun getPeople() {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch {
            getPeople(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<PeopleView> -> {
                            people.value = result.data
                        }
                        is Error -> {
                        }
                    }
                }
        }
    }
}
