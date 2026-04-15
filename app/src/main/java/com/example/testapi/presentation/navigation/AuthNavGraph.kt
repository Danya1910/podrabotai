package com.example.testapi.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.screens.auth.ConfirmEmailScreen
import com.example.testapi.presentation.screens.auth.ForgotPasswordScreen
import com.example.testapi.presentation.screens.auth.LoginScreen
import com.example.testapi.presentation.screens.auth.RecoveryCodeScreen
import com.example.testapi.presentation.screens.auth.RecoveryPasswordScreen
import com.example.testapi.presentation.screens.auth.RegistrationScreen

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.AuthNavGraph(navController: NavHostController) {


    composable(Screen.Login.route) { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        LoginScreen(
            viewModel = viewModel,
            navController = navController
        )
    }


    // Register
    composable(Screen.Registration.route) { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        RegistrationScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    // Confirm Email
    composable(Screen.ConfirmMail.route) { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        ConfirmEmailScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Forgot Password
    composable(Screen.ForgotPassword.route) { backStackEntry ->

        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        ForgotPasswordScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Recovery Code
    composable(Screen.RecoveryCode.route) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        RecoveryCodeScreen(
            viewModel = viewModel,
            navController = navController
        )
    }

    //Recovery Password
    composable(Screen.RecoveryPassword.route) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry("auth_graph")
        }

        val viewModel: LoginViewModel =
            hiltViewModel(parentEntry)

        RecoveryPasswordScreen(
            viewModel = viewModel,
            navController = navController
        )
    }


}
