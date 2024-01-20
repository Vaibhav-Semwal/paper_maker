package com.example.papergenerationapp.app.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.text.StaticLayout
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papergenerationapp.R
import com.example.papergenerationapp.data.AppUiState
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun Summary(
    viewModel: AppViewModel,
    selectedGrade: String,
    modifier: Modifier,
){ 
    Card (modifier = modifier){
        Column (
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Summary", style = MaterialTheme.typography.headlineMedium)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Selected Grade:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = selectedGrade, style = MaterialTheme.typography.bodyMedium)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "No of Selected Questions: ", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "${viewModel.selectedQuestionsSize.value}", style = MaterialTheme.typography.bodyMedium)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Total Number of Marks: ", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = viewModel.totalMarks(), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun PdfGenerationScreen(
    directory: File,
    viewModel: AppViewModel,
    context: Context = LocalContext.current,
    uiState: AppUiState
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Summary(
            viewModel = viewModel,
            selectedGrade = uiState.selectedGrade,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium)))
        Button(
            onClick = {
                generatePDF(context, directory,uiState)
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(60.dp)
                .padding(10.dp),
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontSize = 13.sp
            )
        }
    }

}


fun addLines(y: Float,questionNumber: Int,text: String,marks: String = "", title: Paint, canvas: Canvas){
    val marksText = if(marks != "") " ($marks Mark)" else ""
    val textLength = questionNumber.toString().length + 2 + text.length + marksText.length
    val staticLayout = StaticLayout.Builder
        .obtain("$questionNumber. $text$marksText",0,textLength,TextPaint(title),750)
        .build()
    staticLayout.draw(canvas)
    canvas.translate(0f,y)
}

const val pageHeight = 1200
const val pageWidth = 800

fun generatePDF(context: Context,directory: File, uiState: AppUiState) {

    val pdfDocument = PdfDocument()

    val title = Paint().apply { color = Color.Black.hashCode() }
    title.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    title.textSize = 15f

    lateinit var canvas : Canvas
    val myPage: MutableList<PdfDocument.Page> = mutableListOf()

    val x = 30f     //padding in x axis
    val y = 50f     //padding in y axis
    var currentHeight = y
    val maxHeight = pageHeight - 50f
    var index: Int
    for (text in uiState.selectedQuestions){
        Log.e("size","${myPage.size}")
        if (currentHeight >= maxHeight || myPage.size == 0){
            if (myPage.size >0){
                pdfDocument.finishPage(myPage.last())
            }
            currentHeight = y
            val myPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, myPage.size + 1).create()
            myPage.add(pdfDocument.startPage(myPageInfo))

            canvas = myPage.last().canvas
            canvas.translate(x,y)
        }
        index = uiState.selectedQuestions.indexOf(text)
        addLines(y= y, questionNumber = index+1,text = text.first.question,marks = uiState.selectedQuestionMarks[index],title = title,canvas = canvas)
        currentHeight += y

        Log.d("image resource id","${text.first.questionImage}")
        if(text.first.questionImage != -1   ){
            val image: Drawable? = context.getDrawable(text.first.questionImage)
            val bitmap: Bitmap? = drawableToBitmap(image!!,canvas)
            canvas.drawBitmap(bitmap!!,x,y,title)
            canvas.translate(x,300f)
        }

        text.first.optionsList
            .apply { canvas.translate(x,0f) }
            .forEachIndexed{index,option ->
                addLines(y = y, questionNumber = index+1,text = option,title = title,canvas = canvas)
                currentHeight += y }
            .apply { canvas.translate(-x,0f) }
    }

    pdfDocument.finishPage(myPage.last())
    val file = File(
        directory,
        "${uiState.selectedGrade} ${uiState.selectedSubject} ${uiState.selectedTopic}.pdf"
    )

    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Log.d("MARKS AND QUESTIONS","${uiState.selectedQuestionMarks}")
        Toast.makeText(context, "PDF file generated successfully\n"+ "stored in ${directory.name}", Toast.LENGTH_SHORT).show()
    } catch (ex: IOException) {
        Toast.makeText(context, "Error in generating PDF", Toast.LENGTH_SHORT).show()
        ex.printStackTrace()
    }
    pdfDocument.close()
}

fun drawableToBitmap(drawable: Drawable, canvas: Canvas): Bitmap? {
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    drawable.setBounds(0,0,300,300)
    drawable.draw(canvas)
    return bitmap
}
