package com.deepshooter.blogmultiplatform.data

import com.deepshooter.blogmultiplatform.models.Post
import com.deepshooter.blogmultiplatform.models.PostWithoutDetails
import com.deepshooter.blogmultiplatform.models.User

interface MongoRepository {

    suspend fun addPost(post: Post): Boolean
    suspend fun readMyPosts(skip: Int, author: String): List<PostWithoutDetails>
    suspend fun deleteSelectedPosts(ids: List<String>): Boolean
    suspend fun searchPostsByTittle(query: String, skip: Int): List<PostWithoutDetails>
    suspend fun readSelectedPost(id: String): Post
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean
}