package com.example.pizza.screen

import com.example.pizza.DataStore


data class OrderUiState(
    val pizzaBreads: List<PizzaUiState> = emptyList(),
    val iconIngredients: List<IngredientsTypeUiState> = emptyList(),
)

data class IngredientsTypeUiState(
    val id : Int = 0,
    val image: Int = 0,
    val isSelected : Boolean = false
)

data class PizzaUiState(
    val id : Int = 0,
    val defaultSize: Float = 200f,
    val image: Int = 0,
    val pizzaIngredients: List<IngredientsTypeUiState> = DataStore.pizzaIngredients(),
)