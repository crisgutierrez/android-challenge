package com.talentomobile.starwars.features.people.services

import com.talentomobile.starwars.features.people.models.entity.PeopleEntity
import retrofit2.Response
import retrofit2.http.GET

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
    }

    @GET(PEOPLE)
    suspend fun getPeople(): Response<PeopleEntity>
}
