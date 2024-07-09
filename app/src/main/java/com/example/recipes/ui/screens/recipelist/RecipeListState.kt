package com.example.recipes.ui.screens.recipelist

import com.example.recipes.data.model.response.RecipeResponse

data class RecipeListState(
    val recipeList: List<RecipeResponse> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)