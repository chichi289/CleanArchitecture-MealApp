package com.chichi289.cleanarchitecture.presentation.screens.meal_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chichi289.cleanarchitecture.R
import com.chichi289.cleanarchitecture.domain.model.MealDetails
import com.chichi289.cleanarchitecture.presentation.components.MealProgressBar
import com.chichi289.cleanarchitecture.presentation.components.MealToolbar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealDetailsScreen(
    navHostController: NavHostController,
    mealId: String,
    mealsDetailsViewModel: MealsDetailsViewModel = hiltViewModel()
) {

    val mealDetailsState by mealsDetailsViewModel.mealDetails.collectAsState()

    LaunchedEffect(Unit) {
        mealsDetailsViewModel.getMealDetails(mealId)
    }

    if (mealDetailsState.isLoading) {
        MealProgressBar()
    }

    mealDetailsState.data?.let { mealDetails ->

        val scrollState = rememberScrollState()

        Column(modifier = Modifier.fillMaxWidth()) {
            MealToolbar(title = "${mealDetails.category} - ${mealDetails.name}") {
                navHostController.popBackStack()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .verticalScroll(scrollState)
            ) {
                GlideImage(
                    model = mealDetails.image,
                    contentDescription = mealDetails.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp, max = 250.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(R.string.txt_instructions),
                    style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = mealDetails.instructions,
                    style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(R.string.txt_required_items),
                    style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                    )
                )

                val ingredients = rememberIngredients(mealDetails)
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(500.dp) // TODO("FIX STATIC HEIGHT")
                ) {
                    items(ingredients) { ingredient ->
                        Text(
                            modifier = Modifier.padding(top = 1.dp),
                            text = ingredient,
                            style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberIngredients(mealDetails: MealDetails): List<String> {
    val list = remember {
        LinkedHashSet<String>().apply {
            add("${mealDetails.ingredient1} : ${mealDetails.measure1}\n")
            add("${mealDetails.ingredient2} : ${mealDetails.measure2}\n")
            add("${mealDetails.ingredient3} : ${mealDetails.measure3}\n")
            add("${mealDetails.ingredient4} : ${mealDetails.measure4}\n")
            add("${mealDetails.ingredient5} : ${mealDetails.measure5}\n")
            add("${mealDetails.ingredient6} : ${mealDetails.measure6}\n")
            add("${mealDetails.ingredient7} : ${mealDetails.measure7}\n")
            add("${mealDetails.ingredient8} : ${mealDetails.measure8}\n")
            add("${mealDetails.ingredient9} : ${mealDetails.measure9}\n")
            add("${mealDetails.ingredient10} : ${mealDetails.measure10}\n")
            add("${mealDetails.ingredient11} : ${mealDetails.measure11}\n")
            add("${mealDetails.ingredient12} : ${mealDetails.measure12}\n")
            add("${mealDetails.ingredient13} : ${mealDetails.measure13}\n")
            add("${mealDetails.ingredient14} : ${mealDetails.measure14}\n")
            add("${mealDetails.ingredient15} : ${mealDetails.measure15}\n")
            add("${mealDetails.ingredient16} : ${mealDetails.measure16}\n")
            add("${mealDetails.ingredient17} : ${mealDetails.measure17}\n")
            add("${mealDetails.ingredient18} : ${mealDetails.measure18}\n")
            add("${mealDetails.ingredient19} : ${mealDetails.measure19}\n")
            add("${mealDetails.ingredient20} : ${mealDetails.measure20}\n")
        }.toList()
    }
    return list
}

@Preview
@Composable
fun MealDetailsScreenPreview() {
    MealDetailsScreen(
        navHostController = rememberNavController(), mealId = "52772"
    )
}