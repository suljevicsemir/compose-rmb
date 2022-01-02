package com.semirsuljevic.raiffaisenmobileapp.models.user

data class TokenPair(
    var accessToken: String,
    var refreshToken: String
)