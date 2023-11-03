package com.deepshooter.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator

@Serializable
data class User(
    @SerialName(value = "_id")
    val _id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    val username: String = "",
    val password: String = ""
)
