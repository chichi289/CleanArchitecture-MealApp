package com.chichi289.cleanarchitecture.domain.repository

import com.chichi289.cleanarchitecture.data.model.MealsDTO

interface MealDetailsRepository {
    suspend fun getMealDetails(id: String): MealsDTO
}