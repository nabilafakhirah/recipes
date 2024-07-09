package com.example.recipes.data.repository

import com.example.recipes.data.dao.RecipeDao
import com.example.recipes.data.model.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeStorageRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
): RecipeStorageRepository {
    override fun getSavedRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getSavedRecipes()
    }

    override suspend fun addRecipe(recipeEntity: RecipeEntity) {
        recipeDao.addRecipe(recipeEntity)
    }

    override suspend fun deleteRecipe(recipeEntity: RecipeEntity) {
        recipeDao.deleteRecipe(recipeEntity)
    }

}