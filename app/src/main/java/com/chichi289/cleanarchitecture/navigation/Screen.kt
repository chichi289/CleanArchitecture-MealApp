package com.chichi289.cleanarchitecture.navigation

import com.chichi289.cleanarchitecture.common.Constants.MEAL_ID

sealed class Screen(val route:String){
    object MealSearchScreen:Screen("meal_search/")
    object MealDetailScreen:Screen("meal_detail/{$MEAL_ID}"){
        fun passMealId(id:String):String{
            return "meal_detail/$id"
        }
    }
}
