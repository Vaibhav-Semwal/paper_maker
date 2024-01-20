package com.example.papergenerationapp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.papergenerationapp.ui.theme.PaperGenerationAppTheme

enum class AppScreen(val titleName: String){
    HomeScreen(titleName = "Paper Maker"),
    TopicScreen(titleName = "Select Topic"),
    QuestionSelectionScreen(titleName = "Choose Questions"),
    SelectedQuestionScreen(titleName = "Selected Questions"),
    MarksSelectionScreen(titleName = "Add Marks"),
    PdfGenerationScreen(titleName = "Create PDF")
}

@Preview()
@Composable
fun MainAppPreview(){
    PaperGenerationAppTheme {
        PaperGenerationApp()
    }
}