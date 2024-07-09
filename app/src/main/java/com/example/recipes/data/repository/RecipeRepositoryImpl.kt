package com.example.recipes.data.repository

import com.example.recipes.data.api.RecipeApi
import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi
) : RecipeRepository {
    override fun getRecipes(): Flow<DataResult<List<RecipeResponse>>> = flow {
        try {
            emit(DataResult.Loading())
            val result = recipeApi.getRecipes()
            emit(DataResult.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(DataResult.Error(
                message = "Failed to retrieve recipe list"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                DataResult.Error(
                    message = "Failed to retrieve recipe list"
                ))
        }
    }
}