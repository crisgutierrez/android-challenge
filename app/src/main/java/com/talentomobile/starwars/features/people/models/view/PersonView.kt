package com.talentomobile.starwars.features.people.models.view

import com.talentomobile.starwars.core.extensions.empty

data class PersonView(
    val name: String
) {

    companion object {
        fun empty() =
            PersonView(String.empty())
    }
}
