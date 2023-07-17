package com.example.pizza


interface OrderInteraction {
    fun onChangePizzaSize(position: Int, size: Float)
    fun onIngredientsClick(ingredientsPosition: Int, pizzaPosition: Int)
}