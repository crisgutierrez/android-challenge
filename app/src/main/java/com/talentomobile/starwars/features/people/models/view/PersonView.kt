package com.talentomobile.starwars.features.people.models.view

import com.talentomobile.starwars.core.extensions.empty
import java.io.Serializable

data class PersonView(
    val name: String,
    val height: String?,
    val mass: String?,
    val hair_color: String?,
    val skin_color: String?,
    val eye_color: String?,
    val birth_year: String?,
    val gender: String?
): Serializable {

    companion object {
        fun empty() =
            PersonView(String.empty(), null, null, null, null, null, null, null)
    }
}
