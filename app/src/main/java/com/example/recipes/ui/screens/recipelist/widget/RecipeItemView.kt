package com.example.recipes.ui.screens.recipelist.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipes.R
import com.example.recipes.data.model.response.RecipeResponse
import com.example.recipes.ui.theme.Typography
import com.example.recipes.ui.theme.beige

@Composable
fun RecipeItemView(
    recipe: RecipeResponse,
    onClickItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .clickable {
                onClickItem()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = beige
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp),
                model = recipe.thumb,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.img_load_failed),
                error = painterResource(id = R.drawable.img_load_failed),
                contentDescription = "",
            )
            Column {
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = recipe.name,
                    style = Typography.body1.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = recipe.headline,
                    style = Typography.body2,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemViewPreview() {
    val recipeItem = RecipeResponse(
        calories = "516 kcal",
        carbos = "47 g",
        description = "There\u2019s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!",
        difficulty = 0,
        fats = "8 g",
        headline = "with Sweet Potato Wedges and Minted Snap Peas",
        id = "533143aaff604d567f8b4571",
        image = "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        name = "Crispy Fish Goujons ",
        proteins = "43 g",
        thumb = "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        time = "PT35M"
    )
    RecipeItemView(
        recipe = recipeItem,
        onClickItem = {}
    )
}

