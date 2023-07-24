package com.chichi289.cleanarchitecture.data.repository

import com.chichi289.cleanarchitecture.data.model.MealsDTO
import com.chichi289.cleanarchitecture.data.remote.MealSearchAPI
import com.chichi289.cleanarchitecture.domain.repository.MealSearchRepository

class MealSearchRepositoryImpl(
    private val mealSearchAPI: MealSearchAPI
) : MealSearchRepository {
    override suspend fun getMealSearch(s: String): MealsDTO {
        return mealSearchAPI.getSearchMealList(s)
    }
}