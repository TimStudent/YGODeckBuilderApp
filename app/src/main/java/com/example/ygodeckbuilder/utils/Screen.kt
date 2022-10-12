package com.example.ygodeckbuilder.utils

sealed class Screen(val route: String) {
    object CardSearch : Screen("Card Search")
    object CardDetail : Screen("Card Details")
    object DeckListView: Screen("Deck List")
}
