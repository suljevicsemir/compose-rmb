package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.locations_filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.FilterViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SearchBy

@Composable
fun DistanceFilterRow( filterViewModel: FilterViewModel) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ){
        DistanceFilterButton(
            title = stringResource(id = R.string.locations_filter_distance_closest),
            selected = filterViewModel.selectedSearch.value == SearchBy.Closest,
            onClick = {
                filterViewModel.selectedSearch.value = SearchBy.Closest
            }
        )
        DistanceFilterButton(
            title = stringResource(id = R.string.locations_filter_distance_radius),
            selected = filterViewModel.selectedSearch.value == SearchBy.Radius,
            onClick = {
                filterViewModel.selectedSearch.value = SearchBy.Radius
            }
        )
        DistanceFilterButton(
            title = stringResource(id = R.string.locations_filter_distance_city),
            selected = filterViewModel.selectedSearch.value == SearchBy.City,
            onClick = {
                filterViewModel.selectedSearch.value = SearchBy.City

            }
        )
    }
}

@Composable
fun DistanceFilterButton(title: String, selected: Boolean, onClick: () -> Unit) {
    val width = LocalConfiguration.current.screenWidthDp * 0.28
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .height(45.dp)
            .width(width = width.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp)),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(selected) Yellow400 else White
        )
    ) {
        Text(
            title,
            color = Black
        )
    }
}

@Composable
fun DistanceRadius(filterViewModel: FilterViewModel) {
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
                value = filterViewModel.slideValue.value.toString(),
                onValueChange = {
                    if (it.toFloatOrNull() != null) {
                        if (it.toFloat() <= 100) {
                            filterViewModel.slideValue.value = it.toInt()
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
                filterViewModel.slideValue.value = it.toInt()
            },
            value = filterViewModel.slideValue.value.toFloat(),
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
