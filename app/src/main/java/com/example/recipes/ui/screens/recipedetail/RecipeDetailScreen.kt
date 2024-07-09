package com.example.recipes.ui.screens.recipedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipes.R
import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.ui.theme.Typography
import com.example.recipes.ui.theme.beige

@Composable
fun RecipeDetailScreen(
    recipe: RecipeResponse,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )
            }
        },
    ) {
        Card(
            modifier = modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .clickable {
                    //onClickItem(recipe.id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            backgroundColor = beige
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(300.dp),
                        model = recipe.image,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.img_load_failed),
                        error = painterResource(id = R.drawable.img_load_failed),
                        contentDescription = "",
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = recipe.name,
                        style = Typography.subtitle1,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = recipe.headline,
                        style = Typography.body1.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = recipe.description,
                        style = Typography.body2,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Nutritional Values",
                    style = Typography.body2.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Calories: ${recipe.calories}",
                    style = Typography.body2
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Carbos: ${recipe.carbos}",
                    style = Typography.body2
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Fats: ${recipe.fats}",
                    style = Typography.body2
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Proteins: ${recipe.proteins}",
                    style = Typography.body2
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "Difficulty Level: ${recipe.difficulty}",
                    style = Typography.body2.copy(fontWeight = FontWeight.Bold),
                )
            }
        }
    }
}