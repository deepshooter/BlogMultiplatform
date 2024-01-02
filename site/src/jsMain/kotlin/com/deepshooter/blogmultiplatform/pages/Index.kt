package com.deepshooter.blogmultiplatform.pages

import androidx.compose.runtime.*
import com.deepshooter.blogmultiplatform.components.CategoryNavigationItems
import com.deepshooter.blogmultiplatform.components.NavigationItems
import com.deepshooter.blogmultiplatform.components.OverflowSidePanel
import com.deepshooter.blogmultiplatform.models.ApiListResponse
import com.deepshooter.blogmultiplatform.models.Constants.POSTS_PER_PAGE
import com.deepshooter.blogmultiplatform.models.PostWithoutDetails
import com.deepshooter.blogmultiplatform.sections.HeaderSection
import com.deepshooter.blogmultiplatform.sections.MainSection
import com.deepshooter.blogmultiplatform.sections.PostsSection
import com.deepshooter.blogmultiplatform.util.fetchLatestPosts
import com.deepshooter.blogmultiplatform.util.fetchMainPosts
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.launch

@Page
@Composable
fun HomePage() {

    val scope = rememberCoroutineScope()
    val breakpoint = rememberBreakpoint()
    var overflowOpened by remember { mutableStateOf(false) }
    var mainPosts by remember { mutableStateOf<ApiListResponse>(ApiListResponse.Idle) }
    val latestPosts = remember { mutableStateListOf<PostWithoutDetails>() }
    var latestPostsToSkip by remember { mutableStateOf(0) }
    var showMoreLatest by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        fetchMainPosts(
            onSuccess = { mainPosts = it },
            onError = {}
        )

        fetchLatestPosts(
            skip = latestPostsToSkip,
            onSuccess = { response ->
                if (response is ApiListResponse.Success) {
                    latestPosts.addAll(response.data)
                    latestPostsToSkip += POSTS_PER_PAGE
                    if (response.data.size >= POSTS_PER_PAGE) showMoreLatest = true
                }
            },
            onError = {}
        )

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (overflowOpened) {
            OverflowSidePanel(
                onMenuClose = {
                    overflowOpened = false
                },
                content = {
                    CategoryNavigationItems(vertical = true)
                }
            )
        }

        HeaderSection(
            breakpoint = breakpoint,
            onMenuOpen = { overflowOpened = true }
        )

        MainSection(
            breakpoint = breakpoint,
            posts = mainPosts,
            onClick = {}
        )

        PostsSection(
            breakpoint = breakpoint,
            posts = latestPosts,
            title = "Latest Posts",
            showMoreVisibility = showMoreLatest,
            onShowMore = {
                scope.launch {
                    fetchLatestPosts(
                        skip = latestPostsToSkip,
                        onSuccess = { response ->
                            if (response is ApiListResponse.Success) {
                                if (response.data.isNotEmpty()) {
                                    if (response.data.size < POSTS_PER_PAGE) {
                                        showMoreLatest = false
                                    }
                                    latestPosts.addAll(response.data)
                                    latestPostsToSkip += POSTS_PER_PAGE
                                } else {
                                    showMoreLatest = false
                                }
                            }
                        },
                        onError = {}
                    )
                }
            },
            onClick = { }
        )

    }

}
