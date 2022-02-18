package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.user.Card
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.*
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel
import java.util.*


@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentCreateScreen(
    navController: NavController,
    viewModel: PaymentCreateViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scrollState = rememberScrollState()

    var checked by remember {
        mutableStateOf(false)
    }

    var checkedUrgent by remember {
        mutableStateOf(false)
    }

    InfoDialog(isOpened = viewModel.isDialogOpened.value) {
        viewModel.setDialogState(false)
    }

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)


    val context = LocalContext.current
    var date by remember {
        mutableStateOf("")
    }



    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.payment_create_screen_title),
                navController = navController,
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Info",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                viewModel.setDialogState(true)
                            },
                        tint = Gray200,
                    )
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_from_account))
            AccountInfo(card = Card(transactionNumber = "161300008855564654", balance = 12345.51))
            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_to_account))

            ListSectionSeparator(
                text = stringResource(id = R.string.payment_create_screen_amount),
                actions = {
                    Text(
                        stringResource(id = R.string.payment_create_screen_convert),
                        color = White,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = viewModel.currencyConversion.value,
                        onCheckedChange = {
                            viewModel.currencyConversion.value = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Yellow400,
                            checkedTrackColor = Yellow200,
                            uncheckedTrackColor = Gray200,
                            uncheckedThumbColor = White,
                        )
                    )
                }
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(id = R.string.payment_create_screen_emergency_label),
                    color = White
                )
                Switch(
                    checked = checkedUrgent,
                    onCheckedChange = {
                        checkedUrgent = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Yellow400,
                        checkedTrackColor = Yellow200,
                        uncheckedTrackColor = Gray200,
                        uncheckedThumbColor = White,
                    )
                )
            }
            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_date))
            Row {
                Text(
                    date,
                    color = White
                )
                Box(
                    Modifier
                        .width(2.dp)
                        .height(20.dp)
                        .background(color = White)
                )
                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = "",
                    tint = White,
                    modifier = Modifier.clickable {
                        MaterialDialog(context).show {
                            datePicker(
                                minDate = calendar,
                                currentDate = Calendar.getInstance(),
                                dateCallback = { dialog, datetime ->
                                    date = datetime.get(Calendar.DAY_OF_MONTH).toString()
                                }
                            )
                        }
                    }
                )
            }

            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_save_template))


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .onFocusEvent { focusState ->

                },
                value = viewModel.templateName.value,
                onValueChange = {
                    viewModel.onTemplateNameChange(it)
                },

                placeholder = {
                    Text(
                        stringResource(id = R.string.payment_create_screen_save_hint),
                        color = Gray200
                    )
                },

                textStyle = androidx.compose.ui.text.TextStyle(
                    color = White
                ),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Black,
                    cursorColor = Yellow400,
                ),
            )

            Divider(
                color = Gray200,
                startIndent = 10.dp
            )
            Text(
                stringResource(id = R.string.payment_create_screen_character_limit),
                color = White,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.VerifiedUser,
                    contentDescription = "",
                    tint = Yellow400
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    stringResource(id = R.string.payment_create_screen_bottom_label),
                    color = Gray200,
                    fontSize = 12.sp
                )
            }





        }
    }
}

@Composable
fun AccountInfo(card: Card) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Text(
               stringResource(id = R.string.payment_create_screen_debit_label),
               color = White,
               fontSize = 14.sp
            )
            Text(
                card.transactionNumber,
                color = White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    card.balance.toString(),
                    color = White,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    stringResource(id = R.string.payment_create_screen_currency),
                    color = White,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        IconButton(
            onClick = {

            },
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                tint = Gray200,
                contentDescription = "add card",
            )
        }
    }
}