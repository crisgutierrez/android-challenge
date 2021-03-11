package com.talentomobile.starwars.core.functional

import com.talentomobile.starwars.core.exception.ErrorHandler

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : State<Nothing>()
