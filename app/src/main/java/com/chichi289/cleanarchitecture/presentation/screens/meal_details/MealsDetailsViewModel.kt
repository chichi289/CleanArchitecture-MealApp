package com.chichi289.cleanarchitecture.presentation.screens.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.cleanarchitecture.common.Resource
import com.chichi289.cleanarchitecture.domain.use_case.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealsDetailsViewModel @Inject constructor(
    private val mealDetailsUseCase: GetMealDetailsUseCase
) : ViewModel() {

    private val _mealDetails = MutableStateFlow(MealDetailsState())
    val mealDetails: StateFlow<MealDetailsState> = _mealDetails


    fun getMealDetails(id: String) {
        mealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }

                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data?.get(0))
                }
            }
        }.launchIn(viewModelScope)
    }

}