package com.example.papergenerationapp.app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papergenerationapp.R
import com.example.papergenerationapp.app.components.CommonUiCard
import com.example.papergenerationapp.ui.theme.PaperGenerationAppTheme

@Composable
fun HomeScreen(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        topBar()
        CommonUiCard(
            heading = "Achievements",
            subheading = "Your Progress",
            headingStyle = MaterialTheme.typography.headlineSmall,
            onOptionClick = {},
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )

    }
}

@Composable
fun topBar(){
    Box{
        Image(
            painter = painterResource(id = R.drawable.rectangle_1),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceTint),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = null
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.image_size))){
            Box(modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .size(100.dp)
                .background(Color(0xFFB9B9B9))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(
                    text = "Admin",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
                Text(
                    text = "Student",
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun preview(){
    PaperGenerationAppTheme {
        HomeScreen(onClick = {})
    }
}