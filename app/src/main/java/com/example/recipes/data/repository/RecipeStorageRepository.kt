package com.example.recipes.data.repository

import com.example.recipes.data.model.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeStorageRepository {
    fun getSavedRecipes(): Flow<List<RecipeEntity>>

    suspend fun addRecipe(recipeEntity: RecipeEntity)

    suspend fun deleteRecipe(recipeEntity: RecipeEntity)
}