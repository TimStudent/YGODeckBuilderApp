package com.example.ygodeckbuilder.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ygodeckbuilder.data.domain.CardDomain
import com.example.ygodeckbuilder.utils.CardType
import com.example.ygodeckbuilder.utils.UIState
import com.example.ygodeckbuilder.viewmodel.CardViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ViewModelScreen(
    viewModel: CardViewModel
) {
    Surface(
        modifier = Modifier.background(Color.Black)
    ) {
        UIStates(uiState = viewModel.cards.observeAsState().value, viewModel)
    }
}

@Composable
fun SearchBar(viewModel: CardViewModel, list: List<CardDomain>) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val state = remember {mutableStateOf(false)}
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState.value,
            onValueChange = {
                textState.value = it
                state.value = it.text.isNotEmpty()
            },
            label = { Text(text = "Search a Card") },
            singleLine = true,
            shape = RectangleShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            }
        )
        if(state.value){
            SearchingCards(text = textState.value, cards = list , cardViewModel = viewModel)
        }
        //UIStates(uiState = viewModel.cards.observeAsState().value, viewModel)
    }
}


@Composable
fun UIStates(uiState: UIState?, cardViewModel: CardViewModel) {
    when (uiState) {
        is UIState.LOADING -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                color = Color.Blue,
            )
        }
        is UIState.SUCCESS -> {
            Column {
                uiState.success?.let { SearchBar(cardViewModel, it) }
                uiState.success?.let { Cards(cards = it, cardViewModel) }
            }
            val fusionState = remember { mutableStateOf(false) }
            val synchroState = remember { mutableStateOf(false) }
            val xyzState = remember { mutableStateOf(false) }
            val linkState = remember { mutableStateOf(false) }

            if (fusionState.value) {
                uiState.success?.let { FusionCards(cards = it, cardViewModel = cardViewModel) }
            }

            if (synchroState.value) {
                uiState.success?.let { SynchroCards(cards = it, cardViewModel = cardViewModel) }
            }

            if (xyzState.value) {
                uiState.success?.let { XyzCards(cards = it, cardViewModel = cardViewModel) }
            }

            if (linkState.value) {
                uiState.success?.let { LinkCards(cards = it, cardViewModel = cardViewModel) }
            }
            Column(modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
                    .padding(0.dp)) {
                    Spacer(modifier = Modifier.height(700.dp))
                    Row {
                        FloatingActionButton(onClick = {
                            fusionState.value = true
                            synchroState.value = false
                            xyzState.value = false
                            linkState.value = false
                        }) {
                            Text(text = "Sort by Fusion")
                        }
                        FloatingActionButton(onClick = {
                            fusionState.value = false
                            synchroState.value = true
                            xyzState.value = false
                            linkState.value = false
                        }) {
                            Text("Sort by Synchro")
                        }

                        FloatingActionButton(onClick = {
                            fusionState.value = false
                            synchroState.value = false
                            xyzState.value = true
                            linkState.value = false
                        }) {
                            Text(text = "Sort by Xyz")
                        }
                        FloatingActionButton(onClick = {
                            fusionState.value = false
                            synchroState.value = false
                            xyzState.value = false
                            linkState.value = true
                        }) {
                            Text("Sort by Link")
                        }
                    }
                }
            }

        }
        is UIState.ERROR -> {
            uiState.error
            cardViewModel.getAllCards()
        }
        else -> {

        }
    }
}

@Composable
fun Cards(cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        itemsIndexed(items = cards) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun SearchingCards(text: TextFieldValue, cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        val newList = mutableListOf<CardDomain>()
        for(newCards in cards) {
            if (newCards.name?.contains(text.text) == true || newCards.desc?.contains(text.text) == true) {
                newList.add(newCards)
            }
        }
        itemsIndexed(items = newList) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun FusionCards(cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        val newList = mutableListOf<CardDomain>()
        for (fusionCard in cards) {
            if (fusionCard.type == CardType.FUSION_MONSTER.typeName) {
                newList.add(fusionCard)
            }
        }
        itemsIndexed(items = newList) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun SynchroCards(cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        val newList = mutableListOf<CardDomain>()
        for (synchroCard in cards) {
            if (synchroCard.type == CardType.SYNCHRO_MONSTER.typeName) {
                newList.add(synchroCard)
            }
        }
        itemsIndexed(items = newList) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun XyzCards(cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        val newList = mutableListOf<CardDomain>()
        for (xyz in cards) {
            if (xyz.type == CardType.XYZ_MONSTER.typeName) {
                newList.add(xyz)
            }
        }
        itemsIndexed(items = newList) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun LinkCards(cards: List<CardDomain>, cardViewModel: CardViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.background(Color.Black)
    ) {
        val newList = mutableListOf<CardDomain>()
        for (link in cards) {
            if (link.type == CardType.LINK_MONSTER.typeName) {
                newList.add(link)
            }
        }
        itemsIndexed(items = newList) { position, card ->
            CardItems(card = card, cardViewModel = cardViewModel)
        }
    }
}

@Composable
fun CardItems(card: CardDomain, cardViewModel: CardViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { androidx.compose.animation.core.Animatable(0f) }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(),
                    0
                )
            }
            .draggable(
                state = rememberDraggableState { delta ->
                    coroutineScope.launch {
                        offsetX.snapTo(offsetX.value + delta)
                    }
                },
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    coroutineScope.launch {
                        cardViewModel.deckList1.add(card)
                        Log.d(TAG, cardViewModel.deckList1.joinToString())
                    }
                },
                onDragStopped = {
                    coroutineScope.launch {
                        offsetX.animateTo(targetValue = 0f)
                    }
                }
            ),
        shape = RoundedCornerShape(0.dp),
        elevation = 0.dp,

        ) {
        val state = true
        card.imageURL?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .clickable {

                        if (state) {
                            card.name?.let { it1 -> Log.d(TAG, it1) }
//                        state = false
                        } else {
//                        state = true
                        }
                    },
            )
        }
    }
}

