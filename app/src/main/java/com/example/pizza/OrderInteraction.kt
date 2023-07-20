package com.example.pizza

import androidx.compose.material3.FabPosition


interface OrderInteraction {
    fun onChangePizzaSize(position: Int, size: PizzaSize)
    fun onIngredientsClick(ingredientPosition: Int, pizzaPosition: Int)
}