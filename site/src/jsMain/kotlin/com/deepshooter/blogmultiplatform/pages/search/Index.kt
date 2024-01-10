package com.deepshooter.blogmultiplatform.pages.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deepshooter.blogmultiplatform.models.Constants.CATEGORY_PARAM
import com.deepshooter.blogmultiplatform.util.searchPostsByCategory
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.deepshooter.blogmultiplatform.models.Category

@Page(routeOverride = "query")
@Composable
fun SearchPage() {

    val context = rememberPageContext()

    var postsToSkip by remember { mutableStateOf(0) }

    val hasCategoryParam = remember(key1 = context.route) {
        context.route.params.containsKey(CATEGORY_PARAM)
    }

    val value = remember(key1 = context.route) {
        if (hasCategoryParam) {
            context.route.params.getValue(CATEGORY_PARAM)
        } else {
            ""
        }
    }

    LaunchedEffect(key1 = context.route) {

        if (hasCategoryParam) {
            searchPostsByCategory(
                category = runCatching { Category.valueOf(value) }
                    .getOrElse { Category.Programming },
                skip = postsToSkip,
                onSuccess = {

                },
                onError = {

                }
            )
        }
    }

}