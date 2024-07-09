package com.example.recipes.data.repository

import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.util.DataResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<DataResult<List<RecipeResponse>>>
}