package com.example.pizza.screen


data class OrderUiState(
    val pizzaBreads: List<PizzaUiState> = emptyList()
)
data class PizzaUiState(
    val id : Int = 0,
    val defaultSize: Float = 200f,
    val image: Int = 0,
    val pizzaIngredients: List<IngredientsTypeUiState> = emptyList()
)

data class IngredientsTypeUiState(
    val id : Int = 0,
    val icon : Int = 0,
    val image: Int = 0,
    val isSelected : Boolean = false
)

