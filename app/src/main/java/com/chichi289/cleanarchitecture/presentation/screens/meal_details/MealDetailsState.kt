package com.chichi289.cleanarchitecture.presentation.screens.meal_details

import com.chichi289.cleanarchitecture.domain.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
)
