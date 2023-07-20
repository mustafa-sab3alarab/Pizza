package com.example.pizza.screen

import androidx.lifecycle.ViewModel
import com.example.pizza.DataStore
import com.example.pizza.OrderInteraction
import com.example.pizza.PizzaSize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor() : ViewModel(), OrderInteraction {

    private val _state = MutableStateFlow(OrderUiState())
    val state = _state.asStateFlow()


    init {
        _state.update { it.copy(pizzaBreads = DataStore.pizza()) }
        _state.update {
            it.copy(pizzaBreads = it.pizzaBreads.map {
                it.copy(pizzaIngredients = DataStore.pizzaIngredients())
            })
        }
    }

    override fun onChangePizzaSize(position: Int, size: PizzaSize) {
        val currentState = _state.value
        val updatedPizza = currentState.pizzaBreads.mapIndexed { index, pizzaUiState ->
            if (index == position) {
                pizzaUiState.copy(pizzaSize = size)
            } else {
                pizzaUiState
            }
        }
        val updatedState = currentState.copy(pizzaBreads = updatedPizza)
        _state.value = updatedState
    }


    override fun onIngredientsClick(ingredientPosition: Int, pizzaPosition: Int) {
        _state.update {
            it.copy(
                it.pizzaBreads.mapIndexed { pizzaIndex, pizza ->
                    if (pizzaIndex == pizzaPosition) {
                        pizza.copy(
                            pizzaIngredients = pizza.pizzaIngredients.mapIndexed { index, ingredient ->
                                if (index == ingredientPosition) {
                                    ingredient.copy(isSelected = !ingredient.isSelected)
                                } else {
                                    ingredient.copy(isSelected = ingredient.isSelected)
                                }
                            },
                        )
                    } else {
                        pizza.copy(
                            pizzaIngredients = pizza.pizzaIngredients.mapIndexed { index, ingredient ->
                                ingredient.copy(isSelected = ingredient.isSelected)
                            },
                        )
                    }
                },
                currentPage = pizzaPosition,
            )
        }
    }
}