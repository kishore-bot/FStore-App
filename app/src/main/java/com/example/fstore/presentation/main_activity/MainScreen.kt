package com.example.fstore.presentation.main_activity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fstore.presentation.logIn.LogInScreen
import com.example.fstore.presentation.logIn.LogInViewModel
import com.example.fstore.presentation.navigator.NavigatorScreen
import com.example.fstore.presentation.navigator.navigateAndClearBackStack
import com.example.fstore.presentation.register.RegisterScreen
import com.example.fstore.presentation.register.RegisterViewModel
import com.example.fstore.presentation.routes.LogInScreenRoute
import com.example.fstore.presentation.routes.NavigatorScreenRoute
import com.example.fstore.presentation.routes.RegisterScreenRoute

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainActivityViewModel) {
    val navController = rememberNavController()
    val isLoading by viewModel.isLoading.collectAsState()
    val result by viewModel.result.collectAsState()

    if (!isLoading) {
        val startDestination = if (result) {
            RegisterScreenRoute
        } else {
            NavigatorScreenRoute
        }

        Scaffold(modifier = Modifier.fillMaxSize()) {
            it.calculateTopPadding()
            NavHost(navController = navController, startDestination = startDestination) {
                composable(RegisterScreenRoute) {
                    val registerViewModel = hiltViewModel<RegisterViewModel>()
                    RegisterScreen(
                        viewModel = registerViewModel,
                        onSuccess = {
                            navController.navigateAndClearBackStack(NavigatorScreenRoute)
                        },
                        onLogInClick = {
                            navController.navigate(LogInScreenRoute)
                        },
                    )
                }
                composable(LogInScreenRoute) {
                    val logInViewModel = hiltViewModel<LogInViewModel>()
                    LogInScreen(
                        onBackClick = { navController.popBackStack() },
                        onSuccess = { navController.navigateAndClearBackStack(NavigatorScreenRoute) },
                        viewModel = logInViewModel
                    )
                }
                composable(NavigatorScreenRoute) {
                    NavigatorScreen()
                }
            }
        }
    }
}
