package com.dm.coyago.zentunes.data.network.entities.openwhyd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Track(
    @SerialName("eId")
    val eId: String?= "",
    @SerialName("_id")
    val id: String?= "",
    @SerialName("img")
    val img: String?= "",
    @SerialName("name")
    val name: String?= "",
    @SerialName("nbL")
    val nbL: Int?=-1,
    @SerialName("nbR")
    val nbR: Int?=-1,
    @SerialName("pId")
    val pId: String?= "",
    @SerialName("pl")
    val pl: Pl?=null,
    @SerialName("score")
    val score: Int?=-1,
    @SerialName("trackUrl")
    val trackUrl: String?= "",
    @SerialName("uId")
    val uId: String?= "",
    @SerialName("uNm")
    val uNm: String?= ""
)