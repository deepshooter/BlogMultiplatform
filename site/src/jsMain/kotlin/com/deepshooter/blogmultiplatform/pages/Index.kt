package com.deepshooter.blogmultiplatform.pages

import androidx.compose.runtime.*
import com.deepshooter.blogmultiplatform.components.CategoryNavigationItems
import com.deepshooter.blogmultiplatform.components.NavigationItems
import com.deepshooter.blogmultiplatform.components.OverflowSidePanel
import com.deepshooter.blogmultiplatform.models.ApiListResponse
import com.deepshooter.blogmultiplatform.sections.HeaderSection
import com.deepshooter.blogmultiplatform.sections.MainSection
import com.deepshooter.blogmultiplatform.util.fetchMainPosts
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint

@Page
@Composable
fun HomePage() {

    val scope = rememberCoroutineScope()
    val breakpoint = rememberBreakpoint()
    var overflowOpened by remember { mutableStateOf(false) }
    var mainPosts by remember { mutableStateOf<ApiListResponse>(ApiListResponse.Idle) }

    LaunchedEffect(Unit) {

        fetchMainPosts(
            onSuccess = { mainPosts = it },
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

    }

}
