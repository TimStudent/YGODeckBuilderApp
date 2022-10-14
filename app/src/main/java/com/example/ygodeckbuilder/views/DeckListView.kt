package com.example.ygodeckbuilder.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ygodeckbuilder.viewmodel.CardViewModel

@Composable
fun DeckListScreen(viewModel: CardViewModel) {
    val showList1 = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Button(onClick = {
                if (viewModel.deckList1.isEmpty()) {
                    openDialog.value = true
                } else {
                    showList1.value = true
                }
            }) {
                Text(text = "Deck List 1")
            }

            Button(onClick = { viewModel.deckList1.clear(); showList1.value = false }) {
                Text(text = "Clear Deck List 1")
            }
        }
        if (showList1.value) {
            DeckList1(viewModel)
        }
        if (openDialog.value) {
            AlertDialog(
                title = {Text(text = "ERROR")},
                text = {Text("Deck List is Empty")},
                onDismissRequest = { openDialog.value = false },
                buttons = {
                    Row(
                        modifier = Modifier
                            .padding(all = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { openDialog.value = false }
                        ) {
                            Text("Dismiss")
                        }
                    }
                })
        }
    }
}

@Composable
fun DeckList1(viewModel: CardViewModel) {

    Surface(

    ) {
        Cards(cards = viewModel.deckList1, cardViewModel = viewModel)
    }
    Box {
        FloatingActionButton(onClick = { viewModel.sortByNormalMonster(viewModel.deckList1) }) {
            Text(text = "Sort By Normal Monsters")
        }
    }
}
