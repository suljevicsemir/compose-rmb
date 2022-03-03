package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.maps.android.compose.GoogleMap
import com.semirsuljevic.raiffaisenmobileapp.models.locations.BankBranch
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun BranchListItem(branch: BankBranch, navController: NavController) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {

            }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ){
        Icon(
            imageVector = if (branch.isAtm()) Icons.Outlined.AccountBalance else Icons.Outlined.CreditCard,
            contentDescription = "",
            tint = Gray200,

        )
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(
               branch.name,
               color = White,
            )
            Text(
                "Distance",
                color = Gray200,
                fontSize = 14.sp
            )
        }
        GoogleMap(

        )

    }
}