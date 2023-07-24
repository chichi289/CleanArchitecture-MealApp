package com.chichi289.cleanarchitecture.domain.use_case

import android.util.Log
import com.chichi289.cleanarchitecture.common.Resource
import com.chichi289.cleanarchitecture.data.model.toDomainMeal
import com.chichi289.cleanarchitecture.domain.model.Meal
import com.chichi289.cleanarchitecture.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchMealsUseCase @Inject constructor(
    private val repository: MealSearchRepository
) {

    operator fun invoke(q: String): Flow<Resource<List<Meal>>> = flow {
        Log.e("TAG", "invoke:$q")
        try {
            emit(Resource.Loading())
            val data = repository.getMealSearch(q)
            val domainData =
                if (data.meals != null) data.meals.map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
        }
    }

}