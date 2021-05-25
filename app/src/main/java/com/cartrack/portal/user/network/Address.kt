package com.cartrack.portal.user.network

data class Address(
    val street: String,
    val suite:String,
    val city:String,
    val zipcode: String,
    val geo:Geo
)