package com.example.recipes.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeResponse(
    @Json(name="calories")
    val calories: String,
    @Json(name="carbos")
    val carbos: String,
    @Json(name="description")
    val description: String,
    @Json(name="difficulty")
    val difficulty: Int,
    @Json(name="fats")
    val fats: String,
    @Json(name="headline")
    val headline: String,
    @Json(name="id")
    val id: String,
    @Json(name="image")
    val image: String,
    @Json(name="name")
    val name: String,
    @Json(name="proteins")
    val proteins: String,
    @Json(name="thumb")
    val thumb: String,
    @Json(name="time")
    val time: String,
) : Parcelable