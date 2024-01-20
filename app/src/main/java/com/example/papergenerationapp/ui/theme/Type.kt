package com.example.papergenerationapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.papergenerationapp.R

val AbrilFatface = FontFamily(
    Font(R.font.abril_fatface)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

//    displayMedium = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Bold,
//        fontSize = 20.sp,
//    ),
//    displaySmall = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Bold,
//        fontSize = 20.sp,
//    ),
//    bodySmall = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Bold,
//        fontSize = 12.sp,
//    ),
//    bodyMedium = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Bold,
//        fontSize = 18.sp,
//    ),
//    bodyLarge = TextStyle(
//        fontFamily = Montserrat,
//        fontWeight = FontWeight.Normal,
//        fontSize = 14.sp,
//    )


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)