package com.example.recipes.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipes.util.RECIPE_TABLE

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val calories: String,
    val carbos: String,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val image: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String,
)