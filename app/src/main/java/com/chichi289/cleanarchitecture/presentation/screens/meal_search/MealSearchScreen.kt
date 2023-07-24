package com.chichi289.cleanarchitecture.presentation.screens.meal_search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chichi289.cleanarchitecture.domain.model.Meal
import com.chichi289.cleanarchitecture.navigation.Screen
import com.chichi289.cleanarchitecture.presentation.components.MealProgressBar
import com.chichi289.cleanarchitecture.presentation.components.MealSearchBar

@Composable
fun MealSearchScreen(
    navHostController: NavHostController, viewModel: MealSearchViewModel = hiltViewModel()
) {

    val mealSearchState by viewModel.searchList.collectAsState()

    Log.e("TAG", mealSearchState.toString())

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MealSearchBar(onQuerySearch = { query ->
            viewModel.searchMealList(query)
        })

        if (mealSearchState.isLoading) {
            MealProgressBar()
        }

        mealSearchState.data?.let {

            if (it.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(4.dp)
                ) {
                    items(it) { meal ->
                        MealItem(meal = meal, onClick = { mealId ->
                            navHostController.navigate(Screen.MealDetailScreen.passMealId(mealId))
                        })
                    }
                }
            } else {
                LottieView(
                    modifier = Modifier.fillMaxSize(),
                    assetName = "empty.json"
                )
            }
        } ?: run {
            LottieView(
                modifier = Modifier.fillMaxSize(),
                assetName = "empty.json"
            )
        }
    }
}

@Composable
fun LottieView(
    modifier: Modifier, assetName: String
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName))
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever
    )

    LottieAnimation(modifier = modifier, composition = composition, progress = { progress })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealItem(meal: Meal, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onClick.invoke(meal.id)
            }, elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ), shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = meal.image,
                contentDescription = meal.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 250.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp), text = meal.name, style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview
@Composable
fun MealSearchScreenPreview() {
    MealSearchScreen(
        navHostController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}

@Preview
@Composable
fun MealItemPreview() {
    MealItem(
        meal = Meal(
            id = "1",
            name = "Chicken",
            image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg"
        ),
        onClick = {}
    )
}