package com.deepshooter.blogmultiplatform.navigation

import com.deepshooter.blogmultiplatform.models.Category
import com.deepshooter.blogmultiplatform.models.Constants.CATEGORY_PARAM
import com.deepshooter.blogmultiplatform.models.Constants.POST_ID_PARAM
import com.deepshooter.blogmultiplatform.models.Constants.QUERY_PARAM
import com.deepshooter.blogmultiplatform.models.Constants.UPDATED_PARAM


sealed class Screen(val route: String) {

    object AdminHome : Screen(route = "/admin/")
    object AdminLogin : Screen(route = "/admin/login")
    object AdminCreate : Screen(route = "/admin/create") {
        fun passPostId(id: String) = "/admin/create?${POST_ID_PARAM}=$id"
    }
    object AdminMyPosts : Screen(route = "/admin/myposts") {
        fun searchByTitle(query: String) = "/admin/myposts?${QUERY_PARAM}=$query"
    }
    object AdminSuccess : Screen(route = "/admin/success") {
        fun postUpdated() = "/admin/success?${UPDATED_PARAM}=true"
    }

    object SearchPage : Screen(route = "/search/query") {
        fun searchByCategory(category: Category) =
            "/search/query?${CATEGORY_PARAM}=${category.name}"

        fun searchByTitle(query: String) = "/search/query?${QUERY_PARAM}=$query"
    }

    object PostPage : Screen(route = "/posts/post") {
        fun getPost(id: String) = "/posts/post?${POST_ID_PARAM}=$id"
    }

    object HomePage : Screen(route = "/")

}