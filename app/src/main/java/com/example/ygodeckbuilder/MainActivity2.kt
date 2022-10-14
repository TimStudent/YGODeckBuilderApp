package com.example.ygodeckbuilder

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ygodeckbuilder.data.domain.CardDomain
import com.example.ygodeckbuilder.di.YGODeckBuilderApp
import com.example.ygodeckbuilder.navigation.BottomNavBarItem
import com.example.ygodeckbuilder.navigation.BottomNavigationBar
import com.example.ygodeckbuilder.navigation.Navigation
import com.example.ygodeckbuilder.ui.theme.YGODeckBuilderTheme
import com.example.ygodeckbuilder.utils.UIState
import com.example.ygodeckbuilder.utils.YGODeckBuilderModelFactory
import com.example.ygodeckbuilder.viewmodel.CardViewModel
import com.example.ygodeckbuilder.views.ViewModelScreen
import javax.inject.Inject

private val black = Color(0xFF000000)

class MainActivity2 : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: YGODeckBuilderModelFactory
    private val cardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CardViewModel::class.java]
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        YGODeckBuilderApp.YGODeckBuilderComponent.inject(this)

        setContent{
            YGODeckBuilderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = black
                ) {
                    supportActionBar?.hide()
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavBarItem(
                                    name = "First",
                                    route = "Deck List",
                                    icon = Icons.Default.Home
                            ),
                                BottomNavBarItem(
                                    name = "Second",
                                    route = "Card Search",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavBarItem(
                                    name = "Third",
                                    route = "Card Details",
                                    icon = Icons.Default.Notifications,
                                    badgeCount = 0
                                )
                            ),
                            navController = navController,

                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        Navigation(navController = navController, cardViewModel)
                    }
                    
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2(){

}