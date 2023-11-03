package com.deepshooter.blogmultiplatform.data

import com.deepshooter.blogmultiplatform.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
}