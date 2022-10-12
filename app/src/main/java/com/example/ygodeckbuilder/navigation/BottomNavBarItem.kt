package com.example.ygodeckbuilder.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavBarItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
