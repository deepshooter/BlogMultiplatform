package com.deepshooter.blogmultiplatform.data

import com.deepshooter.blogmultiplatform.models.Post
import com.deepshooter.blogmultiplatform.models.User

interface MongoRepository {

    suspend fun addPost(post: Post): Boolean
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean
}