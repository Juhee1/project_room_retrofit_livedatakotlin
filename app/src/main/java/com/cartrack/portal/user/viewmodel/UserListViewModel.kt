package com.cartrack.portal.user.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cartrack.portal.user.network.GetUserData
import com.cartrack.portal.user.network.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserListViewModel : ViewModel() {

    var userDetailArrayList = MutableLiveData<List<UserDetail>>()
    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun loadData(): LiveData<List<UserDetail>>? {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetUserData::class.java)
        val jsonCall = service.getData()

        jsonCall.enqueue(object : Callback<List<UserDetail>> {
            override fun onResponse(
                call: Call<List<UserDetail>>,
                response: Response<List<UserDetail>>
            ) {
                if (response.isSuccessful) {
                    var data: List<UserDetail> = (response.body() as ArrayList<UserDetail>?)!!
                    userDetailArrayList.value = data
                } else{
                    // can add APIError in case of response failure
                    Log.wtf("UserListViewModel","API Error")
                }
            }

            override fun onFailure(call: Call<List<UserDetail>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return userDetailArrayList
    }
}