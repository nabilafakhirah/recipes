package com.example.recipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.ui.navigation.Destinations.RECIPE_DETAIL_ARGS
import com.example.recipes.ui.navigation.Destinations.RECIPE_DETAIL_ROUTE
import com.example.recipes.ui.navigation.Destinations.RECIPE_HOME_ROUTE
import com.example.recipes.ui.screens.recipedetail.RecipeDetailScreen
import com.example.recipes.ui.screens.recipelist.RecipeListScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RECIPE_HOME_ROUTE
    ) {
        composable(RECIPE_HOME_ROUTE) {
            RecipeListScreen(navController = navController)
        }
        composable(RECIPE_DETAIL_ROUTE) {
            val recipe = navController.previousBackStackEntry?.savedStateHandle?.get<RecipeResponse>(RECIPE_DETAIL_ARGS)
            if (recipe != null) RecipeDetailScreen(recipe = recipe, navController = navController)
        }
    }

}

object Destinations {
    const val RECIPE_HOME_ROUTE = "recipe_home_route"
    const val RECIPE_DETAIL_ROUTE = "recipe_detail_route"
    const val RECIPE_DETAIL_ARGS = "recipe"
}