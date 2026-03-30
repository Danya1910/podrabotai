package com.example.testapi.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun RootNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth_graph"
    ) {

        navigation(
            startDestination = Screen.Login.route,
            route = "auth_graph"
        ) {
            AuthNavGraph(navController)
        }

        navigation(
            startDestination = Screen.EmployeeWork.route,
            route = "main_graph"
        ) {
            MainNavGraph(navController)
        }
    }
}