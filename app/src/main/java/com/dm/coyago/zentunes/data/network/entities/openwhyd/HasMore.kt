package com.dm.coyago.zentunes.data.network.entities.openwhyd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HasMore(
    @SerialName("skip")
    val skip: Int?= -1
)