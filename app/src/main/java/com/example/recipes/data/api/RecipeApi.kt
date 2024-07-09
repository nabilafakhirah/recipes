package com.example.recipes.data.api

import com.example.recipes.data.model.response.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {
    @GET("android-test/recipes.json")
    suspend fun getRecipes(): List<RecipeResponse>
}