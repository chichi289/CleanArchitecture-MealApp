package com.chichi289.cleanarchitecture.domain.use_case

import android.util.Log
import com.chichi289.cleanarchitecture.common.Resource
import com.chichi289.cleanarchitecture.data.model.toDomainMealDetails
import com.chichi289.cleanarchitecture.domain.model.MealDetails
import com.chichi289.cleanarchitecture.domain.repository.MealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(
    private val repository: MealDetailsRepository
) {
    operator fun invoke(id: String): Flow<Resource<List<MealDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(id)
            val domainData = if (!data.meals.isNullOrEmpty()) {
                data.meals.map {
                    it.toDomainMealDetails()
                }
            } else {
                emptyList()
            }
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