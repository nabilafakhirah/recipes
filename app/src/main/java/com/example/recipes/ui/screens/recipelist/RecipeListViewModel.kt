package com.example.recipes.ui.screens.recipelist

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.model.entity.RecipeEntity
import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.data.repository.RecipeRepository
import com.example.recipes.data.repository.RecipeStorageRepository
import com.example.recipes.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val recipeStorageRepository: RecipeStorageRepository
) : ViewModel() {

    private val _state = mutableStateOf(RecipeListState())
    val state: State<RecipeListState> = _state
    private var defaultList = emptyList<RecipeResponse>()


    fun getRecipes() {
        val recipeListResponse = recipeRepository.getRecipes()
        recipeListResponse.onEach { result ->
            when (result) {
                is DataResult.Success -> {
                    defaultList = result.data.orEmpty().sortedBy { it.name }
                    val recipeEntityList = defaultList.map {
                        RecipeEntity(
                            id = it.id,
                            calories = it.calories,
                            carbos = it.carbos,
                            description = it.description,
                            difficulty = it.difficulty,
                            fats = it.fats,
                            headline = it.headline,
                            image = it.image,
                            name = it.name,
                            proteins = it.proteins,
                            thumb = it.thumb,
                            time = it.time
                        )
                    }
                    recipeEntityList.forEach {
                        recipeStorageRepository.addRecipe(it)
                    }
                    _state.value = RecipeListState(
                        recipeList = defaultList,
                        isLoading = false,
                        isError = false,
                    )
                }

                is DataResult.Loading -> {
                    _state.value = RecipeListState(
                        isLoading = true,
                        isError = false,
                    )
                }

                is DataResult.Error -> {
                    _state.value = RecipeListState(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getOfflineRecipes() {
        val recipeList = recipeStorageRepository.getSavedRecipes()
        recipeList.onEach { recipeEntityList ->
            defaultList = recipeEntityList.map {
                RecipeResponse(
                    id = it.id,
                    calories = it.calories,
                    carbos = it.carbos,
                    description = it.description,
                    difficulty = it.difficulty,
                    fats = it.fats,
                    headline = it.headline,
                    image = it.image,
                    name = it.name,
                    proteins = it.proteins,
                    thumb = it.thumb,
                    time = it.time
                )
            }
            _state.value = RecipeListState(
                recipeList = defaultList,
                isLoading = false,
                isError = false,
            )
        }
    }

    fun getFilteredRecipes(filter: String) {
        val filteredList = if (filter != "") {
            defaultList.filter {
                it.name.contains(filter)
            }
        } else {
            defaultList
        }
        _state.value = RecipeListState(
            recipeList = filteredList,
            isLoading = false,
            isError = false,
        )
    }

    fun sortListDesc() {
        val sortedList = defaultList.sortedBy { it.name }
        _state.value = RecipeListState(
            recipeList = sortedList,
            isLoading = false,
            isError = false,
        )
    }

    fun sortListAsc() {
        val sortedList = defaultList.sortedByDescending { it.name }
        _state.value = RecipeListState(
            recipeList = sortedList,
            isLoading = false,
            isError = false,
        )
    }


}