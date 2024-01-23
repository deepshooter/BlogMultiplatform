package com.deepshooter.androidapp.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepshooter.androidapp.data.MongoSync
import com.deepshooter.androidapp.models.Post
import com.deepshooter.androidapp.util.Constants.APP_ID
import com.deepshooter.androidapp.util.RequestState
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _allPosts: MutableState<RequestState<List<Post>>> =
        mutableStateOf(RequestState.Idle)
    val allPosts: State<RequestState<List<Post>>> = _allPosts


    init {
        viewModelScope.launch(Dispatchers.IO) {
            App.create(APP_ID).login(Credentials.anonymous())
            fetchAllPosts()
        }
    }

    private suspend fun fetchAllPosts() {
        withContext(Dispatchers.Main) {
            _allPosts.value = RequestState.Loading
        }
        MongoSync.readAllPosts().collectLatest {
            _allPosts.value = it
        }
    }

}