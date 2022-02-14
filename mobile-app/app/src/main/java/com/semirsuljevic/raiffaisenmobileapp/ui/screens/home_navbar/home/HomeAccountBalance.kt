package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun HomeAccountBalance() {
    Box(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray400)
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ){
                Text(
                    stringResource(id =  R.string.home_debit_card_label),
                    color = White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
                Text(
                    "1613000465465465456",
                    color = White,
                    fontSize = 14.sp
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        "12.345,53",
                        color = White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        stringResource(id = R.string.home_currency),
                        color = White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        stringResource(id = R.string.home_current_balance_label),
                        color = White,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            "12.345,53",
                            color = White,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            stringResource(id = R.string.home_currency),
                            color = White,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
            }
        }
    }
}