package com.example.papergenerationapp.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.papergenerationapp.app.profile.HomeScreen
import com.example.papergenerationapp.app.ui.AppViewModel
import com.example.papergenerationapp.data.AppUiState
import java.io.File

enum class ProfileScreen{ 
    HomeScreen
}

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    viewModel: AppViewModel,
    uiState: AppUiState,
    directory: File = File("")
){
    navigation(
        startDestination = ProfileScreen.HomeScreen.name,
        route = MainScreen.ProfileScreen.name
    ){
        composable(route = ProfileScreen.HomeScreen.name) {
            HomeScreen(onClick = {})
        }
    }
}