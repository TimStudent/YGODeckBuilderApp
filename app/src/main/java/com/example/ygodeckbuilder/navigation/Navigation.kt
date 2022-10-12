package com.example.ygodeckbuilder.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ygodeckbuilder.viewmodel.CardViewModel
import com.example.ygodeckbuilder.views.CardDetailScreen
import com.example.ygodeckbuilder.views.DeckListScreen
import com.example.ygodeckbuilder.views.ViewModelScreen

@Composable
fun Navigation(navController: NavHostController, cardViewModel: CardViewModel) {
    NavHost(navController = navController, startDestination = "Card Search") {
        composable("Card Search") {
            ViewModelScreen(viewModel = cardViewModel)
        }
        composable("Card Details") {
            CardDetailScreen()
        }
        composable("Deck List") {
            DeckListScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavBarItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Blue,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0)
                            BadgedBox(
                                badge = {
                                    Badge { Text(item.badgeCount.toString()) }
                                }) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                    }
                }
            )
        }
    }
}