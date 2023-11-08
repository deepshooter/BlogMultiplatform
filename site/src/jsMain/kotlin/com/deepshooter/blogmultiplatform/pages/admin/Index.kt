package com.deepshooter.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.deepshooter.blogmultiplatform.components.SidePanel
import com.deepshooter.blogmultiplatform.util.Constants.PAGE_WIDTH
import com.deepshooter.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.px


@Page
@Composable
fun HomePage() {
    isUserLoggedIn {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .maxHeight(PAGE_WIDTH.px)
        ) {
            SidePanel(onMenuClick = {})
        }
    }

}