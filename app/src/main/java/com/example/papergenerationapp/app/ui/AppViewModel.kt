package com.example.papergenerationapp.app.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papergenerationapp.data.AppUiState
import com.example.papergenerationapp.data.JsonData
import com.example.papergenerationapp.data.ModelItem
import com.example.papergenerationapp.data.QuestionItem
import com.example.papergenerationapp.data.QuestionListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

sealed interface JsonState {
    data class Success(val json: List<ModelItem>, val questionJson: List<QuestionListItem>) : JsonState
    object Error : JsonState
    object Loading : JsonState
}

class AppViewModel : ViewModel() {

    val bottomNavIndex by mutableStateOf(0)
    var jsonState: JsonState by mutableStateOf(JsonState.Loading)
        private set

    val selectedQuestions: MutableList<Pair<QuestionItem,Boolean>> = mutableListOf()
    var selectedQuestionsSize = mutableStateOf(selectedQuestions.size)
        private set

    val selectedQuestionMarks = mutableListOf<String>()

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private fun loadJsonDataFromAsset(){
        viewModelScope.launch{
            jsonState = JsonState.Loading
            jsonState = withContext(Dispatchers.IO){
                try {
                    JsonState.Success(json = JsonData.loadJson(), questionJson = JsonData.loadQuestionsJson())
                } catch (ioException: IOException) {
                    Log.e("error in viewModel_loadJson","${ioException}")
                    JsonState.Error
                }
            }
        }
    }

    fun totalMarks(): String{
        var totalmarks = 0
        selectedQuestionMarks.forEach{ item ->
            if (item != ""){
                totalmarks += item.toInt()
            }
        }
        return totalmarks.toString()
    }

    fun setGrade(selectedGrade : String){
        Log.e("Selected Grade","${selectedGrade}||${_uiState.value.selectedTopic}")
       _uiState.update { currentState ->
            currentState.copy(selectedGrade = selectedGrade)
       }
    }

    fun setSubject(selectedSubject: String){
        _uiState.update { currentState ->
            currentState.copy(selectedSubject = selectedSubject)
        }
    }

    fun setTopic(selectedTopic : String){
        Log.e("question list selection","${uiState.value.selectedGrade} ${selectedTopic}||")
        _uiState.update { currentState ->
            currentState.copy(selectedTopic = selectedTopic)
        }
    }

    fun updateQuestionsList(question: QuestionItem, isChecked: Boolean){
        if (isChecked) {
            selectedQuestions.add(Pair(question,isChecked))
        } else selectedQuestions.remove(Pair(question,true))
        selectedQuestionsSize.value = selectedQuestions.size
    }

    fun setQuestions(){
        _uiState.update { currentState ->
            currentState.copy(selectedQuestions = selectedQuestions)
        }
        Log.e("the output","${_uiState.value.selectedQuestions.size} || ${_uiState.value.selectedQuestions}")
    }

    fun updateMarksList(marks: String, index: Int){
        selectedQuestionMarks[index] = marks
    }

    fun setMarks(){
        _uiState.update { currentState ->
            currentState.copy(selectedQuestionMarks = selectedQuestionMarks)
        }
    }

    fun resetUiState(){
        _uiState.value = AppUiState()
    }

    init{
        resetUiState()
        loadJsonDataFromAsset()
    }

}