package com.dm.coyago.zentunes.data.network.entities.openwhyd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pl(
    @SerialName("id")
    val id: Int?=-1,
    @SerialName("name")
    val name: String?= ""
)