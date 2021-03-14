package com.talentomobile.starwars.features.people.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.talentomobile.starwars.core.extensions.cancelIfActive
import com.talentomobile.starwars.core.platform.BaseViewModel
import com.talentomobile.starwars.features.people.usecases.GetPersonFavoriteState
import com.talentomobile.starwars.features.people.usecases.PersonFavoriteParams
import com.talentomobile.starwars.features.people.usecases.SetPersonFavoriteState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PersonDetailViewModel(
    private val getPersonFavoriteState: GetPersonFavoriteState,
    private val setPersonFavoriteState: SetPersonFavoriteState
) : BaseViewModel() {

    var isFavorite = MutableLiveData<Boolean>()
    private var getPersonFavoriteStateJob: Job? = null
    private var setPersonFavoriteStateJob: Job? = null

    /**
     * Get [isFavorite] value for the person with the requested [name].
     */
    fun getPersonFavorite(name : String) {
        getPersonFavoriteStateJob.cancelIfActive()
        getPersonFavoriteStateJob = viewModelScope.launch {
           getPersonFavoriteState(name)
                .onEach { isFavorite.value = it }
               .launchIn(viewModelScope)

        }
    }

    /**
     * Set to the person with [name] the new value of [isFavorite].
     * Since we want the user to see that the action he made is immediately we don't wait until we update
     * the database, we update the view with the new value and do this action in background.
     */
    fun setPersonFavorite(name : String, isFavorite: Boolean) {
        setPersonFavoriteStateJob.cancelIfActive()
        setPersonFavoriteStateJob = viewModelScope.launch {
            setPersonFavoriteState(PersonFavoriteParams(name, isFavorite))
                .onEach { /* Nothing to do here*/ }
                .catch { failure -> handleFailure(failure) }
                .launchIn(viewModelScope)
        }
    }
}
