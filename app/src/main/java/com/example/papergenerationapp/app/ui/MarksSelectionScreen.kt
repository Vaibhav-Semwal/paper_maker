package com.example.papergenerationapp.app.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.CommonQuestionCard

@Composable
fun MarksQuestionScreen(
    modifier: Modifier = Modifier,
    selectedGrade: String,
    selectedTopic: String,
    jsonState: JsonState,
    viewModel: AppViewModel
){
    when(jsonState) {
        is JsonState.Success -> {
            if (viewModel.selectedQuestionMarks.size != viewModel.selectedQuestionsSize.value){
                repeat(viewModel.selectedQuestionsSize.value){
                    viewModel.selectedQuestionMarks.add("")
                }
            }
            LazyColumn(modifier) {
                itemsIndexed(viewModel.selectedQuestions) { index, item ->
                    var marks = remember { mutableStateOf(viewModel.selectedQuestionMarks[index]) }
                    Row {
                        MarksField(value = marks.value, onValueChange = {marks.value = it; viewModel.updateMarksList(marks.value,index)} )
                        CommonQuestionCard(
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                            item = item.first,
                            selectedTopic = selectedTopic,
                            selectedGrade = selectedGrade,
                            enableTag = false
                        )
                    }
                }
            }
        }
        else -> null
    }

}

@Composable
fun MarksField(
    value: String = "",
    onValueChange: (String) -> Unit,
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("") },
        modifier = Modifier
            .size(70.dp)
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        ,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small))
    )
}