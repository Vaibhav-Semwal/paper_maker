    package com.example.papergenerationapp.app

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.ui.AppViewModel
import com.example.papergenerationapp.app.ui.HomeScreen
import com.example.papergenerationapp.app.ui.MarksQuestionScreen
import com.example.papergenerationapp.app.ui.PdfGenerationScreen
import com.example.papergenerationapp.app.ui.QuestionScreen
import com.example.papergenerationapp.app.ui.SelectedQuestionScreen
import com.example.papergenerationapp.app.ui.TopicScreen
import com.example.papergenerationapp.ui.theme.PaperGenerationAppTheme
import java.io.File

enum class MainScreen{
    RootScreen,
    AppScreen,
    ProfileScreen
}

@Composable
fun TopNavigtionBar(
    modifier: Modifier = Modifier,
    currentScreen: String = MainScreen.AppScreen.name,
    title: String = AppScreen.HomeScreen.titleName,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
){
    when (currentScreen) {
        AppScreen.HomeScreen.name -> {
            TitleTopAppBar(
                titleText = title
            )
        }
        MainScreen.ProfileScreen.name -> {
            return
        }
        else -> {
            MainTopAppBar(
                titleText = title,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp)
        }
    }
}

@Composable
fun BottomNavigtionBar(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    currentScreen: String = AppScreen.HomeScreen.name,
    navController: NavHostController,
){
    when (currentScreen) {
        AppScreen.QuestionSelectionScreen.name, AppScreen.TopicScreen.name, AppScreen.MarksSelectionScreen.name -> {
            QuestionBottomAppBar(viewModel = viewModel,navController = navController, modifier = modifier)
        }
        AppScreen.SelectedQuestionScreen.name, AppScreen.PdfGenerationScreen.name -> { }
        else -> {
            TitleBottomAppBar(viewModel = viewModel, navController = navController, modifier = modifier)
        }
    }
}

@Composable
fun PaperGenerationApp(
    viewModel: AppViewModel = viewModel() ,
    navController: NavHostController = rememberNavController(),
    directory: File = File(""),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Log.d("the current backstack","${backStackEntry?.destination}")
    var currentScreen = ""
    var title = ""

    try {
        currentScreen = MainScreen.valueOf(backStackEntry?.destination?.route ?: MainScreen.AppScreen.name).name
    }catch (e:Exception){
        Log.d("MainScreen track","$e")
        try {
            var screen = AppScreen.valueOf(backStackEntry?.destination?.route ?: AppScreen.HomeScreen.name)
            currentScreen = screen.name
            title = screen.titleName
        }catch (e : Exception){
            Log.d("AppScreen track","$e")
            try {
                currentScreen = ProfileScreen.valueOf(backStackEntry?.destination?.route ?: ProfileScreen.HomeScreen.name).name
            }catch (e : Exception){

            }
        }
    }
    Scaffold(
        topBar = {
            TopNavigtionBar(
                currentScreen = currentScreen,
                title = title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            BottomNavigtionBar(
                viewModel = viewModel,
                currentScreen = currentScreen,
                navController = navController
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = MainScreen.AppScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MainScreen.RootScreen.name){
                rootScreen(navController = navController)
            }
            navigation(
                startDestination = AppScreen.HomeScreen.name,
                route = MainScreen.AppScreen.name
            ){
                composable(route = AppScreen.HomeScreen.name) {
                    HomeScreen(
                        jsonState = viewModel.jsonState,
                        onClick = {
                            viewModel.setGrade(it)
                            viewModel.setSubject("Mathematics")
                            navController.navigate(AppScreen.TopicScreen.name)
                        }
                    )
                }
                composable(route = AppScreen.TopicScreen.name) {
                    TopicScreen(
                        jsonState = viewModel.jsonState,
                        selectedGrade = uiState.selectedGrade,
                        onClick = {
                            viewModel.setTopic(it)
                            navController.navigate(AppScreen.QuestionSelectionScreen.name)
                        }
                    )
                }
                composable(route = AppScreen.QuestionSelectionScreen.name) {
                    QuestionScreen(
                        jsonState = viewModel.jsonState,
                        viewModel = viewModel,
                        selectedGrade = uiState.selectedGrade,
                        selectedTopic = uiState.selectedTopic,
                        modifier = Modifier.padding(
                            dimensionResource(id = R.dimen.padding_small),
                        )
                    )
                }
                composable(route = AppScreen.SelectedQuestionScreen.name) {
                    SelectedQuestionScreen(
                        jsonState = viewModel.jsonState,
                        viewModel = viewModel,
                        selectedGrade = uiState.selectedGrade,
                        selectedTopic = uiState.selectedTopic,
                        modifier = Modifier.padding(
                            dimensionResource(id = R.dimen.padding_small),
                        )
                    )
                }
                composable(route = AppScreen.MarksSelectionScreen.name){
                    MarksQuestionScreen(
                        selectedGrade = uiState.selectedGrade,
                        selectedTopic = uiState.selectedTopic,
                        jsonState = viewModel.jsonState,
                        viewModel = viewModel
                    )
                }
                composable(route = AppScreen.PdfGenerationScreen.name){
                    PdfGenerationScreen(directory = directory,uiState = uiState, viewModel = viewModel)
                }
            }
            profileNavGraph(navController = navController, viewModel = viewModel, uiState = uiState)
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T{
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


@Composable
fun rootScreen(
    navController: NavHostController
){
    Row {
        Button(onClick = { navController.navigate(MainScreen.AppScreen.name) }) {
            Text(text = "Create A paper")
        }
        Button(onClick = { navController.navigate(MainScreen.ProfileScreen.name) }) {
            Text(text = "ProfileScreen")
        }
    }
}

@Preview
@Composable
fun preview(){
    PaperGenerationAppTheme {
        PaperGenerationApp()
    }
}