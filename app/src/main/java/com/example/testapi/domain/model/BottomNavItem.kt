package com.example.testapi.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: Int,
    val isFiltered: Boolean? = null
)
