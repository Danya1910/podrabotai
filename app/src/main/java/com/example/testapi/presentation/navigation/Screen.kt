package com.example.testapi.presentation.navigation

import android.net.Uri
import com.example.testapi.domain.model.AdvertisementFilter

sealed class Screen(val route: String) {
    object Registration : Screen("registration")
    object ConfirmMail : Screen("confirmMail")
    object Login : Screen("login")
    object ForgotPassword : Screen("forgotPassword")
    object RecoveryPassword : Screen("recoveryPassword")
    object RecoveryCode : Screen("recoveryCode")

    object ChangePassword : Screen("changePassword")

    object EmployeeWork : Screen("employeeWork") {}
    object EmployerWork : Screen("employerWork")
    object DetailedAdvertisement : Screen("detailedAdvertisement/{jobId}") {
        fun passJobId(jobId: Int): String = "detailedAdvertisement/$jobId"
    }

    object Favorites : Screen("favorites")
    object History : Screen("history")
    object Filter : Screen("filter")

    object CreateAdvertisement : Screen("createAd")
    object UpdateAdvertisement : Screen("updateAdvertisement/{jobId}") {
        fun passJobId(jobId: Int): String = "updateAdvertisement/$jobId"
    }

    object MyAdvertisements : Screen("myAd")

    object EmployeeProfile : Screen("employeeProfile")

    object Chats : Screen("chats")
    object Chat : Screen("chat/{penpalId}?jobId={jobId}") {

        fun passArgs(penpalId: Int, jobId: Int? = null): String {
            return if (jobId != null) {
                "chat/$penpalId?jobId=$jobId"
            } else {
                "chat/$penpalId"
            }
        }
    }
}