package com.example.papergenerationapp.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.CommonUiCard
import com.example.papergenerationapp.ui.theme.PaperGenerationAppTheme

@Composable
fun HomeScreen(
    jsonState: JsonState = JsonState.Loading,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
){

    when(jsonState) {
        is JsonState.Success ->
            LazyColumn(modifier = modifier) {
                items(jsonState.json) { item ->
                    CommonUiCard(
                        heading = item.grade,
                        headingStyle = MaterialTheme.typography.headlineSmall,
                        onOptionClick = onClick,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        else -> null
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    PaperGenerationAppTheme {
        HomeScreen(onClick = { })
    }
}