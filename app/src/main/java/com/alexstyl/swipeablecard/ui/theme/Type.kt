package com.alexstyl.swipeablecard.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alexstyl.swipeablecard.R

val inter = FontFamily(
    Font(R.font.inter)
)
val Typography = Typography(
    defaultFontFamily = inter,
    body1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)