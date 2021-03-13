package com.talentomobile.starwars.features.people.usecases

import com.talentomobile.starwars.core.functional.Error
import com.talentomobile.starwars.core.functional.State
import com.talentomobile.starwars.core.functional.Success
import com.talentomobile.starwars.features.people.models.view.PeopleView
import com.talentomobile.starwars.features.people.services.PeopleService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface PeopleRepository {

    fun getPeople(): Flow<State<PeopleView>>

    class Network(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PeopleService
    ) : PeopleRepository {

        override fun getPeople() =
            flow {
                emit(people())
            }
                .catch { emit(Error(Throwable("s"))) }
                .flowOn(ioDispatcher)

        private suspend fun people() = service.getPeople()
            .run {
                if (isSuccessful && body() != null) {
                    Success(body()!!.toPeople().toPeopleView())
                } else {
                    Error(Throwable("s"))
                }
            }
    }
}
