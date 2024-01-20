package com.example.papergenerationapp.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.QuestionList
import com.example.papergenerationapp.data.QuestionItem

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier,
    selectedGrade: String,
    selectedTopic: String,
    jsonState: JsonState,
    viewModel: AppViewModel
){
    var questionList = mutableListOf<Pair<QuestionItem,Boolean>>()

    when(jsonState) {
        is JsonState.Success -> {
           jsonState.questionJson.forEach{ item ->
               if (item.topic == "$selectedGrade $selectedTopic"){
                   item.questionList.forEach{item ->
                       questionList.add(Pair(item,false))
                   }
                   return@forEach
               }
           }
            QuestionList(
                viewModel = viewModel,
                selectedGrade = selectedGrade,
                selectedTopic = selectedTopic,
                modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                questionList = questionList)
        }
        else -> null
    }

}

@Preview
@Composable
fun Preview(){
    QuestionScreen(
        jsonState = AppViewModel().jsonState,
        viewModel = AppViewModel(),
        selectedTopic = "Class 11",
        selectedGrade = "Algebra"
    )
}