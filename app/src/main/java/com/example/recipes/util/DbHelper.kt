package com.example.recipes.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

class ListConverter {

    @TypeConverter
    fun fromStringList(value: List<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }
}