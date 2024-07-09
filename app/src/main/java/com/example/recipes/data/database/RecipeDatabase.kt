package com.example.recipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipes.data.dao.RecipeDao
import com.example.recipes.data.model.entity.RecipeEntity
import com.example.recipes.util.ListConverter

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract val recipeDao: RecipeDao
}