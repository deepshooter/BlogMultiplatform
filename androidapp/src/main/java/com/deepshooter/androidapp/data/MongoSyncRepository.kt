package com.deepshooter.androidapp.data

import com.deepshooter.androidapp.models.Post
import com.deepshooter.androidapp.util.RequestState
import kotlinx.coroutines.flow.Flow

interface MongoSyncRepository {
    fun configureTheRealm()
    fun readAllPosts(): Flow<RequestState<List<Post>>>
    fun searchPostsByTitle(query: String): Flow<RequestState<List<Post>>>
}