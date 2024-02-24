package com.dm.coyago.zentunes.data.network.entities.openwhyd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MusicRecomendations(
    @SerialName("hasMore")
    val hasMore: HasMore?=null,
    @SerialName("tracks")
    val tracks: List<Track>?= emptyList()
)