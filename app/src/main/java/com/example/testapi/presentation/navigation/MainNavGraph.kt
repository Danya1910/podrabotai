package com.example.testapi.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testapi.presentation.screens.auth.ChangePasswordScreen
import com.example.testapi.presentation.auth.ChatScreen
import com.example.testapi.presentation.auth.EmployeeChatsScreen
import com.example.testapi.presentation.auth.CreateAdvertisementScreen
import com.example.testapi.presentation.auth.DetailedAdvertisementScreen
import com.example.testapi.presentation.auth.EmployeeProfileScreen
import com.example.testapi.presentation.auth.EmployeeWorkScreen
import com.example.testapi.presentation.auth.EmployerChatsScreen
import com.example.testapi.presentation.auth.EmployerProfileScreen
import com.example.testapi.presentation.auth.EmployerWorkScreen
import com.example.testapi.presentation.auth.FavoritesAdvertisementsScreen
import com.example.testapi.presentation.auth.FilterScreen
import com.example.testapi.presentation.auth.HistoryOfAdvertisementsScreen
import com.example.testapi.presentation.auth.MyAdvertisementsScreen
import com.example.testapi.presentation.auth.UpdateAdvertisementScreen
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.viewModels.ChatViewModel
import com.example.testapi.presentation.viewModels.DaDataViewModel
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.viewModels.UserViewModel

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.MainNavGraph(navController: NavHostController) {

    //Employee Work
    composable(Screen.EmployeeWork.route) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)

        EmployeeWorkScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Employer Work
    composable(Screen.EmployerWork.route) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)

        EmployerWorkScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Mine Advertisements
    composable(Screen.MyAdvertisements.route) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)

        MyAdvertisementsScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Filter
    composable(Screen.Filter.route) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)
        val daDataViewModel: DaDataViewModel = hiltViewModel()

        FilterScreen(
            viewModel = viewModel,
            daDataViewModel = daDataViewModel,
            navController = navController
        )
    }

    //Create Adv
    composable(Screen.CreateAdvertisement.route) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)
        val daDataViewModel: DaDataViewModel = hiltViewModel()

        CreateAdvertisementScreen(
            viewModel = viewModel,
            daDataViewModel = daDataViewModel,
            navController = navController
        )
    }

    //Update Adv
    composable(
        route = Screen.UpdateAdvertisement.route,
        arguments = listOf(
            navArgument("jobId") {type = NavType.IntType}
        )
    ) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)
        val jobId = backStackEntry.arguments?.getInt("jobId") ?: 0
        val daDataViewModel: DaDataViewModel = hiltViewModel()


        UpdateAdvertisementScreen(
            viewModel = viewModel,
            navController = navController,
            daDataViewModel = daDataViewModel,
            jobId = jobId
        )

    }

    //Detailed Advertisement
    composable(
        route = Screen.DetailedAdvertisement.route,
        arguments = listOf(
            navArgument("jobId") { type = NavType.IntType }
        )
    ) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)
        val jobId = backStackEntry.arguments?.getInt("jobId") ?: 0

        DetailedAdvertisementScreen(
            viewModel = viewModel,
            navController = navController,
            jobId = jobId
        )
    }

    //Favorites
    composable(
        route = Screen.Favorites.route
    ) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)

        FavoritesAdvertisementsScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //History of adv
    composable(
        route = Screen.History.route
    ) {backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("main_graph") }

        val viewModel: AdvertisementViewModel = hiltViewModel(parentEntry)

        HistoryOfAdvertisementsScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //employee profile
    composable(
        route = Screen.EmployeeProfile.route,
    ) {
        val viewModel: UserViewModel = hiltViewModel()
        EmployeeProfileScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //employer profile
    composable(
        route = Screen.EmployerProfile.route,
    ) {
        val viewModel: UserViewModel = hiltViewModel()
        EmployerProfileScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //changePassword
    composable(
        route = Screen.ChangePassword.route
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        ChangePasswordScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //employee chats
    composable(route = Screen.EmployeeChats.route) {
        val viewModel: ChatViewModel = hiltViewModel()

        EmployeeChatsScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //employer chats
    composable(route = Screen.EmployerChats.route) {
        val viewModel: ChatViewModel = hiltViewModel()

        EmployerChatsScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //chat
    composable(
        route = "chat/{penpalId}?jobId={jobId}",
        arguments = listOf(
            navArgument("penpalId") {
                type = NavType.IntType
            },
            navArgument("jobId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) { backStackEntry ->
        val viewModel: ChatViewModel = hiltViewModel()
        val adViewModel: AdvertisementViewModel = hiltViewModel()
        val penpalId = backStackEntry.arguments?.getInt("penpalId") ?: -1
        val jobId = backStackEntry.arguments?.getInt("jobId") ?: -1

        ChatScreen(
            viewModel = viewModel,
            adViewModel = adViewModel,
            navController = navController,
            penpalId = penpalId,
            jobId = jobId
        )
    }

}