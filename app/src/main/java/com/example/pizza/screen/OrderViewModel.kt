package com.example.pizza.screen

import androidx.lifecycle.ViewModel
import com.example.pizza.DataStore
import com.example.pizza.OrderInteraction
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
        _state.update {
            it.copy(
                pizzaBreads = DataStore.pizza(),
                iconIngredients = DataStore.iconIngredients()
            )
        }
    }

    override fun onChangePizzaSize(position: Int, size: Float) {
        val currentState = _state.value
        val updatedPizza = currentState.pizzaBreads.mapIndexed { index, pizzaUiState ->
            if (index == position) {
                pizzaUiState.copy(defaultSize = size)
            } else {
                pizzaUiState
            }
        }
        val updatedState = currentState.copy(pizzaBreads = updatedPizza)
        _state.value = updatedState
    }

    override fun onIngredientsClick(ingredientsPosition: Int, pizzaPosition: Int) {
        val current = _state.value.pizzaBreads[pizzaPosition].pizzaIngredients[ingredientsPosition].isSelected
        _state.value.pizzaBreads[pizzaPosition].pizzaIngredients[ingredientsPosition].copy(isSelected = !current)
    }
}