package com.example.pizza

import com.example.pizza.screen.IngredientsTypeUiState
import com.example.pizza.screen.PizzaUiState

object DataStore {

    fun pizza() = listOf(
        PizzaUiState(id = 0, image = R.drawable.bread_1),
        PizzaUiState(id = 1,image = R.drawable.bread_2),
        PizzaUiState(id = 2,image = R.drawable.bread_3),
        PizzaUiState(id = 3,image = R.drawable.bread_4),
        PizzaUiState(id = 4,image = R.drawable.bread_5)
    )

    fun iconIngredients() = listOf(
        IngredientsTypeUiState(id = 0,R.drawable.ic_basil),
        IngredientsTypeUiState(id = 1,R.drawable.ic_onion),
        IngredientsTypeUiState(id = 2,R.drawable.ic_mushroom),
        IngredientsTypeUiState(id = 3,R.drawable.ic_broccoli),
        IngredientsTypeUiState(id = 4,R.drawable.ic_sausage)
    )

    fun pizzaIngredients() = listOf(
        IngredientsTypeUiState(id = 0,R.drawable.image_basil),
        IngredientsTypeUiState(id = 1,R.drawable.image_onion),
        IngredientsTypeUiState(id = 2,R.drawable.image_mushroom),
        IngredientsTypeUiState(id = 3,R.drawable.image_broccoli),
        IngredientsTypeUiState(id = 3,R.drawable.image_sausage),
    )
}