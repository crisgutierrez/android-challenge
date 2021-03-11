package com.talentomobile.starwars.core.platform

import android.content.Context
import com.talentomobile.starwars.core.extensions.networkInfo

class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
