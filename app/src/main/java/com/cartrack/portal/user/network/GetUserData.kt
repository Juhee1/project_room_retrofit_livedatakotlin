package com.cartrack.portal.user.network

import retrofit2.Call
import retrofit2.http.GET

interface GetUserData {

    @GET("/users")
    fun getData() : Call<List<UserDetail>>

}