package com.semirsuljevic.raiffaisenmobileapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200

@Composable
fun CenteredTitleAppBar(
    title: String,
    navController: NavController,
    actions: @Composable (RowScope.() -> Unit)? = null,
    implyLeading: Boolean = true
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier.fillMaxWidth(),
    ) {
        Box(Modifier.height(48.dp)) {
            LeadingComposable(
                implyLeading = implyLeading,
                navController = navController
            )
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScreenTitle(text = title)
              }

            ActionsContent(actions = actions)
            }
        }

    }

@Composable
private fun LeadingComposable(implyLeading: Boolean, navController: NavController) {
    if(implyLeading) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack()},
                enabled = true,
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = Gray200
                )
            }
        }
    }
}

@Composable
private fun ActionsContent(actions: @Composable() (RowScope.() -> Unit)?) {
    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
        actions?.invoke(this)
    }
}
