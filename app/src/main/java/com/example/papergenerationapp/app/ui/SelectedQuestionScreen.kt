package com.example.papergenerationapp.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.QuestionList

@Composable
fun SelectedQuestionScreen(
    modifier: Modifier = Modifier,
    selectedGrade: String,
    selectedTopic: String,
    jsonState: JsonState,
    viewModel: AppViewModel,
    onClick: (String) -> Unit = { }
){
    when(jsonState) {
        is JsonState.Success -> {
            QuestionList(
                viewModel = viewModel,
                selectedGrade = selectedGrade,
                selectedTopic = selectedTopic,
                modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                questionList = viewModel.selectedQuestions)
        }
        else -> null
    }

}