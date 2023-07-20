package com.example.pizza.screen

import com.example.pizza.PizzaSize


data class OrderUiState(
    val pizzaBreads: List<PizzaUiState> = emptyList(),
    val currentPage: Int = 0,
)

data class PizzaUiState(
    val id: Int = 0,
    val pizzaSize: PizzaSize = PizzaSize.MEDIUM,
    val image: Int = 0,
    val pizzaIngredients: List<IngredientsTypeUiState> = emptyList()
)

data class IngredientsTypeUiState(
    val id: Int = 0,
    val icon: Int = 0,
    val image: Int = 0,
    val isSelected: Boolean = false
)

