package com.deepshooter.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.deepshooter.blogmultiplatform.components.AdminPageLayout
import com.deepshooter.blogmultiplatform.util.isUserLoggedIn
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun CreatePage() {
    isUserLoggedIn {
        CreateScreen()
    }
}


@Composable
fun CreateScreen() {

    AdminPageLayout {

    }

}