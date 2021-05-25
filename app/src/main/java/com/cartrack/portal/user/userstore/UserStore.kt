package com.cartrack.portal.user.userstore

import android.content.Context
import com.cartrack.portal.user.view.CarTrackApp

class UserStore {
    companion object {
        private val PREFEENCE_NAME = "cartrack_user_pref"
        private val context: Context
            get() = CarTrackApp.appContext

        val isUserLoggedIn: Boolean
            get() = username.isNotEmpty() && password.isNotEmpty()

        var username: String
            get() {
                val mySharedPreferences = context.getSharedPreferences(PREFEENCE_NAME, 0)
                return mySharedPreferences.getString("username", "").toString()
            }
            set(value) {
                val mySharedPreferences = context.getSharedPreferences(PREFEENCE_NAME, 0)
                mySharedPreferences.edit().putString("username", value).apply()
            }

        var password: String
            get() {
                val mySharedPreferences = context.getSharedPreferences(PREFEENCE_NAME, 0)
                return mySharedPreferences.getString("password", "").toString()
            }
            set(value) {
                val mySharedPreferences = context.getSharedPreferences(PREFEENCE_NAME, 0)
                mySharedPreferences.edit().putString("password", value).apply()
            }

        fun deleteAll(){
            context.getSharedPreferences(PREFEENCE_NAME, 0).edit().clear().commit();
        }
    }
}