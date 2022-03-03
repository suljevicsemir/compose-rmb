package com.semirsuljevic.raiffaisenmobileapp.models.locations

import com.semirsuljevic.raiffaisenmobileapp.models.City
import com.semirsuljevic.raiffaisenmobileapp.models.Location

data class BankBranch (
    val id: String,
    val location: Location,
    val name: String,
    val city: City,
    val contact: String,
    val branchType: BranchType,
    val branchServiceType: BranchServiceType,
    val atmType: String,
    val atmFilter: ATMFilter
) {
    fun isAtm() : Boolean {
        return  branchType.id == "9597cffd-63cb-48e2-a724-83da80199840"
    }

}