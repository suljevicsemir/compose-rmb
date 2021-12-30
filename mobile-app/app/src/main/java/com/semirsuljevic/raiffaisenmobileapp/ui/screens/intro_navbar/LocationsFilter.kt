package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationsFilterScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column {
        CenteredTitleAppBar(title = stringResource(id = R.string.locations_filter_title), navController = navController)
        Column (
            Modifier.padding(horizontal = 10.dp)
        ){
            Spacer(modifier = Modifier.height(20.dp))
            LocationsFilterContainer(
                title = stringResource(id = R.string.locations_filter_distance_label),
                content = {
                    DistanceFilterRow()
                }
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun LocationsFilterContainer(
    title: String,
    content: @Composable() () -> Unit
) {
    Column(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Gray400)
            .padding(vertical = 14.dp)
    ) {
        Text(
            title,
            color = White,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Divider(
            color = White,
            thickness = 1.dp,
        )
        Spacer(modifier = Modifier.height(14.dp))
        content()
        Spacer(modifier = Modifier.height(24.dp))
        DistanceCity()

    }
}

@Composable
fun DistanceFilterRow() {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        DistanceFilterButton(title = stringResource(id = R.string.locations_filter_distance_closest))
        DistanceFilterButton(title = stringResource(id = R.string.locations_filter_distance_radius))
        DistanceFilterButton(title = stringResource(id = R.string.locations_filter_distance_city))
    }
}

@Composable
fun DistanceFilterButton(title: String) {
    val width = LocalConfiguration.current.screenWidthDp * 0.28
    Button(
        onClick = {

        },
        modifier = Modifier
            .height(45.dp)
            .width(width = width.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp)),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow400
        )
    ) {
        Text(
            title,
            color = Black
        )
    }
}


@Composable
fun DistanceRadius() {
    var slideValue by remember {
        mutableStateOf(1)
    }
    Column (
        Modifier.padding(horizontal = 10.dp)
    )
    {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                "Radius",
                color = White
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicTextField(
                value = slideValue.toString(),
                onValueChange = {
                    if (it.toFloatOrNull() != null) {
                        if (it.toFloat() <= 100) {
                            slideValue = it.toInt()
                        }
                    }
                },
                textStyle = TextStyle(
                    color = White,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(size = 6.dp))
                    .fillMaxWidth(fraction = 0.7f)
                    .background(color = Black)
                    .padding(vertical = 8.dp, horizontal = 30.dp),
                cursorBrush = Brush.verticalGradient(
                    0.0f to Yellow400,
                    1.0f to Yellow400,
                    startY = 0.0f,
                    endY = 100.0f
                )
            )
        }
        Slider(
            onValueChange = {
                slideValue = it.toInt()
            },
            onValueChangeFinished = {

            },
            value = slideValue.toFloat(),
            valueRange = 1f..100f,
            steps = 100,
            colors = SliderDefaults.colors(
                thumbColor = White,
                activeTickColor = Yellow400,
                inactiveTickColor = Gray200
            )
        )
        Row(
            Modifier.fillMaxWidth(1f)
        ) {
            Text(
                "1km",
                color = Gray200
            )
            Spacer(Modifier.weight(1f))
            Text(
                "100km",
                color = Gray200
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun DistanceCity() {
    var expanded  by remember {
        mutableStateOf(false)
    }
    var selectedOptionText by remember {
        mutableStateOf("")
    }

    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(1f)
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            textStyle = TextStyle(
                color = Gray200
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.padding(vertical = 0.dp, horizontal = 0.dp).background(color = Black)
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    modifier = Modifier
                        .background(color = Black)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = selectionOption,
                        color = Gray200,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }


    }


}