package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.transactions.RMBTransaction
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import java.util.*

@Composable
fun HomeTransactions() {

    val list = listOf<RMBTransaction>(
        RMBTransaction(name = "HUHU REGISTRACIJA", amount = 5.0, senderId = "JA", date = Calendar.getInstance()),
        RMBTransaction(name = "PLATA SQA", amount = 1200.0, senderId = "NE", date = Calendar.getInstance()),
        RMBTransaction(name = "PATIKE", amount = 220.0, senderId = "JA", date = Calendar.getInstance())
    )

    Box(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Gray400)
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.home_transactions_headline),
                        color = White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "",
                        tint = Gray200,
                        modifier = Modifier.clickable {

                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    list.forEach {
                        HomeTransactionListItem(rmbTransaction = it)
                    }
                    Text(
                        text = stringResource(id = R.string.home_transactions_see_all),
                        color = White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun HomeTransactionListItem(rmbTransaction: RMBTransaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, top = 8.dp)
            .drawBehind {
                drawLine(
                    color = Gray200,
                    start = Offset(0f, this.size.height + 25),
                    end = Offset(this.size.width, this.size.height + 25),
                    strokeWidth = 3f
                )
            }
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowRight,
            contentDescription = "",
            tint = if(rmbTransaction.isIncome()) Color.Green else Gray200,
            modifier = Modifier
                .offset(x = (-4).dp)
                .rotate(degrees = if (rmbTransaction.isIncome()) 90.0f else -90.0f)

        )
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                rmbTransaction.name,
                color = White,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            TransactionDate(rmbTransaction = rmbTransaction)
        }

        Text(
            rmbTransaction.getAmount(),
            color = if(rmbTransaction.isIncome()) Color.Green else Gray200,
            fontSize = 18.sp,
            maxLines = 1
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            stringResource(id = R.string.home_currency),
            color = White,
            maxLines = 1
        )

    }
}

@Composable
private fun TransactionDate(rmbTransaction: RMBTransaction) {
    Text(
        "${stringResource(id = rmbTransaction.getMonth())} ${rmbTransaction.getDay()}, ${rmbTransaction.getYear()}",
        color = Gray200,
        fontSize = 14.sp
    )
}