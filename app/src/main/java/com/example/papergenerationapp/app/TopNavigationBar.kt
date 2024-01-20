package com.example.papergenerationapp.app

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papergenerationapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTopAppBar(
    modifier: Modifier = Modifier,
    titleText: String = ""
){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceTint),
        title = {
        Row (
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
//                Image(
//                    modifier = Modifier
//                        .size(dimensionResource(id = R.dimen.image_size))
//                        .padding(dimensionResource(id = R.dimen.padding_small)),
//                    painter = painterResource(id = R.drawable.ic_woof_logo),
//                    contentDescription = null)
            Text(
                text = titleText,
                style = MaterialTheme.typography.displayLarge
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    titleText: String = "",
    navigateUp: () -> Unit,
    canNavigateBack: Boolean
){
    TopAppBar(
        title = { Text(text = titleText, style = MaterialTheme.typography.displayLarge) },
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }

        }
    )
}
