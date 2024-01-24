package com.deepshooter.androidapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.deepshooter.androidapp.models.Category
import com.deepshooter.androidapp.screens.category.CategoryScreen
import com.deepshooter.androidapp.screens.category.CategoryViewModel
import com.deepshooter.androidapp.screens.home.HomeScreen
import com.deepshooter.androidapp.screens.home.HomeViewModel
import com.deepshooter.androidapp.util.Constants.CATEGORY_ARGUMENT

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {

            val viewModel: HomeViewModel = viewModel()
            var searchBarOpened by remember { mutableStateOf(false) }
            var query by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }

            HomeScreen(
                posts = viewModel.allPosts.value,
                searchedPosts = viewModel.searchedPosts.value,
                query = query,
                searchBarOpened = searchBarOpened,
                active = active,
                onActiveChange = { active = it },
                onQueryChange = { query = it },
                onCategorySelect = { navController.navigate(Screen.Category.passCategory(it)) },
                onSearchBarChange = { opened ->
                    searchBarOpened = opened
                    if (!opened) {
                        query = ""
                        active = false
                        viewModel.resetSearchedPosts()
                    }
                },
                onSearch = viewModel::searchPostsByTitle,
                onPostClick = {}
            )
        }

        composable(
            route = Screen.Category.route,
            arguments = listOf(navArgument(name = CATEGORY_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            val viewModel: CategoryViewModel = viewModel()
            val selectedCategory =
                it.arguments?.getString(CATEGORY_ARGUMENT) ?: Category.Programming.name
            CategoryScreen(
                posts = viewModel.categoryPosts.value,
                category = Category.valueOf(selectedCategory),
                onBackPress = { navController.popBackStack() },
                onPostClick = {}
            )
        }

        composable(route = Screen.Details.route) {

        }
    }
}