package com.deepshooter.blogmultiplatform.data

import com.deepshooter.blogmultiplatform.models.Newsletter
import com.deepshooter.blogmultiplatform.models.Post
import com.deepshooter.blogmultiplatform.models.PostWithoutDetails
import com.deepshooter.blogmultiplatform.models.User
import com.deepshooter.blogmultiplatform.models.Category

interface MongoRepository {

    suspend fun addPost(post: Post): Boolean
    suspend fun updatePost(post: Post): Boolean
    suspend fun readMyPosts(skip: Int, author: String): List<PostWithoutDetails>
    suspend fun readMainPosts(): List<PostWithoutDetails>
    suspend fun readLatestPosts(skip: Int): List<PostWithoutDetails>
    suspend fun readSponsoredPosts(): List<PostWithoutDetails>
    suspend fun readPopularPosts(skip: Int): List<PostWithoutDetails>
    suspend fun deleteSelectedPosts(ids: List<String>): Boolean
    suspend fun searchPostsByCategory(category: Category, skip: Int): List<PostWithoutDetails>
    suspend fun searchPostsByTittle(query: String, skip: Int): List<PostWithoutDetails>
    suspend fun readSelectedPost(id: String): Post
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean
    suspend fun subscribe(newsletter: Newsletter): String
}