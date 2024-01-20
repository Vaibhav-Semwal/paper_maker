package com.example.papergenerationapp.app

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.ui.AppViewModel

data class BottomAppBarItem(
    val title : String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false
)

val navigationItems = listOf(
//    BottomAppBarItem(
//        title = "Home",
//        selectedIcon = Icons.Filled.Home,
//        unselectedIcon = Icons.Rounded.Home,
//        route = MainScreen.AppScreen.HomeScreen.name
//    ),
    BottomAppBarItem(
        title = "Create",
        selectedIcon = Icons.Filled.Create,
        unselectedIcon = Icons.Rounded.Create,
        route = MainScreen.AppScreen.name
    ),
    BottomAppBarItem(
        title = "Profile",
        selectedIcon = Icons.Filled.PersonOutline,
        unselectedIcon = Icons.Rounded.PersonOutline,
        route = MainScreen.ProfileScreen.name
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBottomAppBar(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var selectedItemIndex by remember { mutableStateOf(viewModel.bottomNavIndex) }
    NavigationBar(modifier = Modifier) {
        navigationItems.forEachIndexed{ index, item ->
            val iconScale by animateFloatAsState(targetValue = if (selectedItemIndex == index) 1.2f else 1f)
            val navigationAnim by animateFloatAsState(targetValue =  if (selectedItemIndex == index) 1.2f else 1f)

            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                modifier = Modifier.weight(
                   navigationAnim
                ),
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.hasNews){
                                Badge()
                            }
                        },
                        modifier = Modifier.scale(iconScale)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_small))
                                .animateContentSize(
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title,
                            )
                            Text(text = item.title,style = MaterialTheme.typography.bodySmall)
                        }
                    }
                })
        }
    }
}

@Composable
fun QuestionBottomAppBar(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    BottomAppBar(
        modifier = modifier,
        actions = {
            val selected by viewModel.selectedQuestionsSize
            TextButton(onClick = { navController.navigate(AppScreen.SelectedQuestionScreen.name) }) {
                Text(text = "${selected} Selected")
            }
        },
        floatingActionButton = {
           Button(onClick = {
               if (navController.currentBackStackEntry?.destination?.route == AppScreen.QuestionSelectionScreen.name){
                   viewModel.setQuestions()
                   navController.navigate(AppScreen.MarksSelectionScreen.name)
               }else if (navController.currentBackStackEntry?.destination?.route == AppScreen.MarksSelectionScreen.name){
                   viewModel.setMarks()
                   navController.navigate(AppScreen.PdfGenerationScreen.name)
               } },
               shape = RoundedCornerShape(10.dp)
               ) {
               Row(
                   Modifier
                       .align(Alignment.CenterVertically)
                       .padding(dimensionResource(id = R.dimen.padding_small))){
                   Icon(
                       imageVector = Icons.Rounded.Check,
                       contentDescription = null,
                       )
               }
           }
        }
    )
}