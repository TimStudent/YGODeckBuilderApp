package com.example.ygodeckbuilder.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ygodeckbuilder.data.domain.CardDomain
import com.example.ygodeckbuilder.utils.UIState
import com.example.ygodeckbuilder.viewmodel.CardViewModel

@Composable
fun ViewModelScreen(
    viewModel: CardViewModel
) {
    Surface(

    ) {
        UIStates(uiState = viewModel.cards.observeAsState().value, viewModel)
    }
}
@Composable
fun UIStates(uiState: UIState?, cardViewModel: CardViewModel) {
    Surface(modifier = Modifier.background(Color.Black)) {
        
    }
    when(uiState) {
        is UIState.LOADING -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.Blue,
            )
        }
        is UIState.SUCCESS -> {
            uiState.success?.let { Cards(cards = it) }
        }
        is UIState.ERROR   -> {
            uiState.error
            cardViewModel.getAllCards()
        }
        else -> {

        }
    }
}
@Composable
fun Cards(cards: List<CardDomain>) {
    LazyColumn() {
        itemsIndexed(items = cards) { position, card ->
            CardItems(card = card)
        }
    }
}

@Composable
fun CardItems(card: CardDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ){
        Column(modifier = Modifier.background(Color.DarkGray)) {
            card.name?.let { Text(text = it, color = Color.White) }
            card.imageURL?.let { Image(painter = rememberAsyncImagePainter(model = it), contentDescription = null) }
            card.desc?.let { Text(text = it, color = Color.White) }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                card.tcgPlayerPrice?.let { Text(text = "TCG Player Price: $it", color = Color.White) }
                card.cardMarketPrice?.let { Text(text = "Card Market Price: $it", color = Color.White) }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                card.ebayPrice?.let { Text(text = "Ebay Price: $it", color = Color.White) }
                card.amazonPrice?.let { Text(text = "Amazon Price: $it", color = Color.White) }
            }
        }
    }
}
