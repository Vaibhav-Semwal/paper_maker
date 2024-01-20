package com.example.papergenerationapp.data

data class AppUiState(
    val selectedGrade: String = "",
    val selectedSubject: String = "",
    val selectedTopic: String = "",
    val selectedQuestions: List<Pair<QuestionItem,Boolean>> = mutableListOf(),
    val selectedQuestionMarks: List<String> = mutableListOf()
)