package com.example.starwarsgraphql.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

data class CustomTypography(
    val h2Default: TextStyle = TextStyle(
        color = TextDark,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    val h2LowEmphasis: TextStyle = TextStyle(
        color = TextLight,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    val h2HighEmphasis: TextStyle = TextStyle(
        color = TextEmphasis,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    val p1Default: TextStyle = TextStyle(
        color = TextDark,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val p1LowEmphasis: TextStyle = TextStyle(
        color = TextLight,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
)

val LocalRavnTypography = compositionLocalOf { CustomTypography() }

val MaterialTheme.ravnTypography: CustomTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalRavnTypography.current