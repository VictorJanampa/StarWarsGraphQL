package com.example.starwarsgraphql.composables

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.starwarsgraphql.R
import com.example.starwarsgraphql.ui.theme.StarWarsGraphQLTheme
import com.example.starwarsgraphql.ui.theme.TextLight
import com.example.starwarsgraphql.ui.theme.padding
import com.example.starwarsgraphql.ui.theme.ravnTypography

@Composable
fun TopBar(title: String) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            Modifier.fillMaxWidth()
        ){
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.padding.medium)
            )
        }
    }
}

@Composable
fun NoticeCell() {
    Text(
        text = "Failed To Load Data",
        style = MaterialTheme.ravnTypography.h2HighEmphasis,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.padding.medium)
    )
}

@Composable
fun PersonCell(
    name: String,
    species: String,
    homeworld: String,
    modifier: Modifier
) {
    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.padding.medium)
        ){
            Column (
                modifier = Modifier
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.ravnTypography.h2Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier

                )
                Text(
                    text = "$species from $homeworld",
                    style = MaterialTheme.ravnTypography.p1LowEmphasis,
                    textAlign = TextAlign.Start,
                    modifier = Modifier

                )
            }
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Arrow Forward",
                tint = MaterialTheme.colors.primary,
            )
        }
        Divider(
            color = TextLight,
            thickness = 1.dp,
            startIndent = MaterialTheme.padding.medium
        )
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.ravnTypography.h2Default,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.padding.large,
                start = MaterialTheme.padding.medium,
                end = MaterialTheme.padding.medium,
                bottom = MaterialTheme.padding.small,
            )
    )
}

@Composable
fun DataCell(
    trait: String,
    dataTrait: String
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.padding.medium)
        ){
            Text(
                text = trait,
                style = MaterialTheme.ravnTypography.h2LowEmphasis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(
                height = 0.dp,
                width = 16.dp)
            )
            Text(
                text = dataTrait,
                textAlign = TextAlign.End,
                style = MaterialTheme.ravnTypography.h2Default,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            color = TextLight,
            thickness = 1.dp,
            startIndent = MaterialTheme.padding.medium
        )
    }
}

@Composable
fun VehicleCell(
    vehicleName: String,
) {
    Column {
        Text(
            text = vehicleName,
            style = MaterialTheme.ravnTypography.h2LowEmphasis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    MaterialTheme.padding.medium
                )
        )
        Divider(
            color = TextLight,
            thickness = 1.dp,
            startIndent = MaterialTheme.padding.medium
        )
    }
}


@Composable
fun LoadingCell() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painterResource(R.drawable.spinner),
            contentDescription = "",
            tint = TextLight,
            modifier = Modifier.size(
                 24.dp
            )
        )
        Text(
            text = "Loading",
            style = MaterialTheme.ravnTypography.h2LowEmphasis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.padding.small,
                    vertical = MaterialTheme.padding.medium
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    StarWarsGraphQLTheme {
        TopBar(title = "People")
    }
}

@Preview(showBackground = true)
@Composable
fun NoticeCellPreview() {
    StarWarsGraphQLTheme {
        NoticeCell()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingCellPreview() {
    StarWarsGraphQLTheme {
        LoadingCell()
    }
}

@Preview(showBackground = true)
@Composable
fun PersonCellPreview() {
    StarWarsGraphQLTheme {
        PersonCell(
            "Luke Skywalker",
            "Human",
            "Tatooine",
            Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SectionHeaderPreview() {
    StarWarsGraphQLTheme {
        SectionHeader(
            "General Information"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DataCellPreview() {
    StarWarsGraphQLTheme {
        DataCell(
            "Eye Color",
            "Blue"
        )
    }
}

