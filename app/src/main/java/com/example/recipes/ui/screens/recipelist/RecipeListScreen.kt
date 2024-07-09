package com.example.recipes.ui.screens.recipelist

import NetworkMonitor
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipes.ui.navigation.Destinations.RECIPE_DETAIL_ARGS
import com.example.recipes.ui.navigation.Destinations.RECIPE_DETAIL_ROUTE
import com.example.recipes.ui.screens.recipelist.widget.RecipeItemView
import com.example.recipes.ui.screens.recipelist.widget.SearchBarView
import com.example.recipes.ui.theme.beige

@Composable
fun RecipeListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val networkMonitor = NetworkMonitor(context = LocalContext.current)
    LaunchedEffect(key1 = true) {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                viewModel.getRecipes()
            }

            override fun onLost(network: Network) {
                viewModel.getOfflineRecipes()
            }
        }
        networkMonitor.registerNetworkCallback(networkCallback)
    }
    val state = viewModel.state
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBarView(
                onSearch = {
                    viewModel.getFilteredRecipes(it)
                },
                onSortAsc = {
                    viewModel.sortListAsc()
                },
                onSortDesc = {
                    viewModel.sortListDesc()
                }
            )
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    color = beige
                )
            }
            LazyColumn {
                items(state.value.recipeList.size) { index ->
                    RecipeItemView(
                        recipe = state.value.recipeList[index],
                        onClickItem = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = RECIPE_DETAIL_ARGS,
                                value = state.value.recipeList[index]
                            )
                            navController.navigate(RECIPE_DETAIL_ROUTE)
                        },
                    )
                }
            }
        }
    }
}