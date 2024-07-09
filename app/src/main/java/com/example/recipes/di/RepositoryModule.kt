package com.example.recipes.di

import com.example.recipes.data.api.RecipeApi
import com.example.recipes.data.dao.RecipeDao
import com.example.recipes.data.repository.RecipeRepository
import com.example.recipes.data.repository.RecipeRepositoryImpl
import com.example.recipes.data.repository.RecipeStorageRepository
import com.example.recipes.data.repository.RecipeStorageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(recipeApi: RecipeApi): RecipeRepository {
        return RecipeRepositoryImpl(recipeApi)
    }

    @Provides
    @Singleton
    fun provideStorageRepository(recipeDao: RecipeDao): RecipeStorageRepository {
        return RecipeStorageRepositoryImpl(recipeDao)
    }
}