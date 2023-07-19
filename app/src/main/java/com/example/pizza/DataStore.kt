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

    fun pizzaIngredients() = listOf(
        IngredientsTypeUiState(id = 0, icon = R.drawable.ic_basil, image = R.drawable.image_basil, isSelected = false),
        IngredientsTypeUiState(id = 1, icon = R.drawable.ic_onion, image = R.drawable.image_onion, isSelected = false),
        IngredientsTypeUiState(id = 2, icon = R.drawable.ic_mushroom, image = R.drawable.image_mushroom, isSelected = false),
        IngredientsTypeUiState(id = 3, icon = R.drawable.ic_broccoli, image = R.drawable.image_broccoli, isSelected = false),
        IngredientsTypeUiState(id = 4, icon = R.drawable.ic_sausage, image = R.drawable.image_sausage, isSelected = false),
    )
}