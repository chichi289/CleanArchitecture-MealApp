package com.chichi289.cleanarchitecture.presentation.screens.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi289.cleanarchitecture.common.Resource
import com.chichi289.cleanarchitecture.domain.use_case.SearchMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val searchMealsUseCase: SearchMealsUseCase
) : ViewModel() {

    private val _mutableSearchList = MutableStateFlow(MealSearchState())
    val searchList: StateFlow<MealSearchState> = _mutableSearchList

    fun searchMealList(query: String) {
        searchMealsUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mutableSearchList.value = MealSearchState(isLoading = true)
                }

                is Resource.Error -> {
                    _mutableSearchList.value =
                        MealSearchState(error = it.message ?: "An unexpected error occurred")
                }

                is Resource.Success -> {
                    _mutableSearchList.value = MealSearchState(data = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}