package com.example.papergenerationapp.app.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.CommonUiCard
import com.example.papergenerationapp.app.components.LoadingScreen

@Composable
fun TopicScreen(
    modifier: Modifier = Modifier,
    jsonState: JsonState = AppViewModel().jsonState,
    selectedGrade: String,
    onClick: (String) -> Unit = { }
){
    var topicList: List<String> = listOf()

    when(jsonState) {
        is JsonState.Success ->
            LazyColumn(modifier = modifier) {
                jsonState.json.forEach{
                    if (it.grade.equals(selectedGrade)){
                        topicList = it.topicList
                    }
                }
                Log.d("uistate grade","|||$selectedGrade|||")
                items(topicList) { item ->
                    CommonUiCard(
                        heading = item,
                        headingStyle = MaterialTheme.typography.headlineSmall,
                        onOptionClick = { onClick(item) } ,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        is JsonState.Loading -> LoadingScreen()
        is JsonState.Error -> null
    }
}
