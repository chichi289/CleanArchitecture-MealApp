package com.chichi289.cleanarchitecture.data.repository

import com.chichi289.cleanarchitecture.data.model.MealsDTO
import com.chichi289.cleanarchitecture.data.remote.MealSearchAPI
import com.chichi289.cleanarchitecture.domain.repository.MealDetailsRepository

class MealDetailsRepositoryImpl(
    private val mealSearchAPI: MealSearchAPI
): MealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}