package com.chichi289.cleanarchitecture.presentation.screens.meal_search

import com.chichi289.cleanarchitecture.domain.model.Meal

data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)
