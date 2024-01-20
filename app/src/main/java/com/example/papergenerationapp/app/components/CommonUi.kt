package com.example.papergenerationapp.app.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.ui.AppViewModel
import com.example.papergenerationapp.data.QuestionItem
import com.example.papergenerationapp.ui.theme.PaperGenerationAppTheme
import com.example.papergenerationapp.ui.theme.Typography

// the text button that we see on the screen
@Composable
fun LoadingScreen(){
    Card(Modifier.fillMaxSize()){
        Text(text = "Loading")
    }
}

@Composable
fun CardInformation(
    modifier: Modifier = Modifier,
    heading: String,
    headingStyle: TextStyle,
    subheading: String = "",
) {
    Column (modifier = modifier) {
        Text(
            text = heading,
            style = headingStyle,
            textAlign = TextAlign.Start,
            modifier = Modifier
        )
        if(subheading == ""){
            Text(
                text = subheading,
                style = Typography.bodySmall,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

// the expanding button (if there are any options)
@Composable
private fun SelectionButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            modifier = Modifier,
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.primary,
        )
    }

}

// main common card that is needed to be utilised
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonUiCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = -1,
    heading: String,
    headingStyle: TextStyle,
    subheading: String = "",
    onOptionClick: (String) -> Unit,
    optionList: List<String> = listOf()
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {onOptionClick(heading)}
    ){
        Column (modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )),
            verticalArrangement = Arrangement.Center
            ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                CardInformation(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                    heading = heading,
                    headingStyle = headingStyle,
                    subheading = subheading
                )
            }
            if(image != -1){
                Log.d("the drawable resource is ","$image")
//                AsyncImage(
//                    model = Image(painter = painterResource(id = image), contentDescription = null),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxSize(0.5f)
//                        .padding(dimensionResource(id = R.dimen.padding_small))
//                )
                Image(
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(R.dimen.padding_medium),
                            vertical = dimensionResource(R.dimen.padding_small)
                        )
                    ,
                    contentScale = ContentScale.FillHeight,
                    painter = painterResource(id = image),
                    contentDescription = null
                )
            }
            if (optionList.isNotEmpty()) {
                Options(
                    optionList = optionList,
                    onOptionClick = onOptionClick
                )
            }
        }
    }
}

// all the list of the options (if there are any)
@Composable
fun Options(
    modifier: Modifier = Modifier,
    optionList: List<String>,
    onOptionClick: (String) -> Unit
) {
    Column(modifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.padding_medium),
            bottom = dimensionResource(id = R.dimen.padding_medium),
        )
    ) {
        optionList.forEach {
            option -> OptionItems(
                option = option
            )
        }
    }
}

// refers to the question items created
@Composable
fun OptionItems(
    modifier: Modifier = Modifier,
    option: String,
){
    Text(
        modifier = modifier
            .padding(vertical = 5.dp),
        text = option,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun QuestionCheckBoxes(
    modifier: Modifier = Modifier,
    questionItem: QuestionItem,
    viewModel: AppViewModel = AppViewModel(),
    checked: Boolean
){
    var isChecked = remember {mutableStateOf(checked)}
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = { isChecked.value = it
            viewModel.updateQuestionsList(questionItem, it)
        }
    )
}

@Composable
fun QuestionTags(
    tagList: List<String>,
    modifier: Modifier = Modifier
){
    LazyRow(modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))){
        items(tagList){items ->
            Card (
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceTint), 
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)/2)
            ){
                Text(
                    text = items,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
                )
            }
        }
    }
}

@Composable
fun CommonQuestionCard(
    item: QuestionItem,
    modifier: Modifier = Modifier,
    selectedTopic: String,
    selectedGrade: String,
    enableTag: Boolean = true
){
    Column {
        CommonUiCard(
            heading = item.question + "( ${selectedGrade + selectedTopic} )",
            optionList = item.optionsList,
            image = item.questionImage,
            headingStyle = MaterialTheme.typography.bodyLarge,
            modifier = modifier,
            onOptionClick = { }
        )
        if (enableTag){
            QuestionTags(
                tagList = item.tagList,
            )
        }
    }
}

@Composable
fun QuestionList(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    questionList: MutableList<Pair<QuestionItem, Boolean>>,
    selectedGrade: String,
    selectedTopic: String,
){
    LazyColumn(){
        items(questionList){item ->
            Row {
                QuestionCheckBoxes(
                    questionItem = item.first,
                    viewModel = viewModel,
                    checked = item.second
                )
                CommonQuestionCard(
                    modifier = modifier,
                    item = item.first,
                    selectedTopic = selectedTopic,
                    selectedGrade = selectedGrade
                )
            }
        }
    } 
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(showSystemUi = true)
@Composable
fun Preview(){
    PaperGenerationAppTheme {
        Column {
            CommonUiCard(
                modifier = Modifier.padding(16.dp),
                heading = "dtw 1?",
                subheading = "this is the subheading",
                headingStyle = MaterialTheme.typography.headlineSmall,
                onOptionClick = {},
                optionList = listOf("option1","option2","option3","option4")
            )
            CommonUiCard(
                modifier = Modifier.padding(16.dp),
                heading = "dtw 2?",
                image = R.drawable.class_10_circle_1,
                subheading = "this is the subheading",
                headingStyle = MaterialTheme.typography.headlineSmall,
                onOptionClick = {},
                optionList = listOf("option1","option2","option3","option4")
            )
            CommonUiCard(
                modifier = Modifier.padding(16.dp),
                heading = "dtw 3?",
                subheading = "this is the subheading",
                headingStyle = MaterialTheme.typography.headlineSmall,
                onOptionClick = {},
                optionList = listOf("option1","option2","option3","option4")
            )
        }
    }
}
