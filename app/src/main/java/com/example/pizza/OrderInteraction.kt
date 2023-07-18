package com.example.pizza

import com.example.pizza.screen.IngredientsTypeUiState


interface OrderInteraction {
    fun onChangePizzaSize(position: Int, size: Float)
    fun onIngredientsClick(ingredient: IngredientsTypeUiState, pizzaPosition: Int)
}