package com.example.pizza.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizza.OrderInteraction
import com.example.pizza.R
import com.example.pizza.screen.composable.PizzaPager
import com.example.pizza.ui.theme.Brown

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderScreen(
    viewModel: OrderViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()
    OrderContent(state, pagerState, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderContent(
    state: OrderUiState,
    pagerState: PagerState,
    orderInteraction: OrderInteraction,
) {
    Column(Modifier.fillMaxSize()) {

        OrderScreenAppBar(modifier = Modifier.padding(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = Modifier.size(256.dp),
                painter = painterResource(id = R.drawable.plate), contentDescription = "plate"
            )

            PizzaPager(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                pagerState = pagerState
            ) { page ->

                Box {
                    Image(
                        modifier = Modifier.size(state.pizzaBreads[page].defaultSize.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = state.pizzaBreads[page].image),
                        contentDescription = "pizza"
                    )
                }

                state.pizzaBreads[pagerState.currentPage].pizzaIngredients.forEach {
                    IngredientsAnimation(it)
                }
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$20",
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "S", modifier = Modifier
                .clip(CircleShape)
                .padding(4.dp)
                .clickable { orderInteraction.onChangePizzaSize(pagerState.currentPage, 176f) }
            )
            Text(text = "M", modifier = Modifier
                .clip(CircleShape)
                .clickable { orderInteraction.onChangePizzaSize(pagerState.currentPage, 200f) }
                .padding(4.dp))
            Text(text = "L", modifier = Modifier
                .clip(CircleShape)
                .clickable { orderInteraction.onChangePizzaSize(pagerState.currentPage, 224f) }
                .padding(4.dp))
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Customize your pizza",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray
            )
        )

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.pizzaBreads[pagerState.currentPage].pizzaIngredients) { index: Int, item: IngredientsTypeUiState ->
                IconIngredients(state = item, isSelected = item.isSelected,
                    onClick = { orderInteraction.onIngredientsClick(index ,pagerState.currentPage) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(Brown),
            shape = RoundedCornerShape(8.dp),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "shopping icon"
            )
            Text(text = "Add to Cart")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IngredientsAnimation(state: IngredientsTypeUiState) {
    AnimatedVisibility(
        visible = state.isSelected,
        enter = scaleIn(initialScale = 4f) + fadeIn(),
        exit = fadeOut()
    ) {
        Image(
            painter = painterResource(id = state.image),
            contentDescription = "plate",
        )
    }
}

@Composable
private fun IconIngredients(
    state: IngredientsTypeUiState,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)
        .clickable { onClick() }
        .background(if (isSelected) Color.Green.copy(alpha = 0.1f) else Color.Transparent)
        .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = state.icon),
            contentDescription = "ingredients"
        )
    }
}

@Composable
private fun OrderScreenAppBar(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "back arrow"
        )
        Text(text = "Pizza")
        Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "favorite")
    }
}