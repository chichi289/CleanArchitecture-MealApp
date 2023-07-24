package com.chichi289.cleanarchitecture.domain.repository

import com.chichi289.cleanarchitecture.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealSearch(s: String): MealsDTO
}