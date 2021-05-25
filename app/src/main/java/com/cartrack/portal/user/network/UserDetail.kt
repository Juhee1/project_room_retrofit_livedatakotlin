package com.cartrack.portal.user.network

data class UserDetail(
    val id : Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address
)