package com.cartrack.portal.user.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cartrack.portal.user.R
import com.cartrack.portal.user.userstore.UserStore
import com.cartrack.portal.user.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    lateinit var strUsername: String
    lateinit var strPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        context = this@LoginActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        btnSignUp.setOnClickListener {

            UserStore.deleteAll()
            strUsername = txtUsername.text.toString().trim()
            strPassword = txtPassword.text.toString().trim()

            if (strUsername.isEmpty()) {
                txtUsername.error = "Please enter the username"
            } else if (strPassword.isEmpty()) {
                txtPassword.error = "Please enter the password"
            } else {
                loginViewModel.insertData(context, strUsername, strPassword)
                lblInsertResponse.setTextColor(ContextCompat.getColor(this,R.color.green))
                lblInsertResponse.text = "Sign up successful"
            }
        }

        btnLogin.setOnClickListener {
            UserStore.deleteAll()
            strUsername = txtUsername.text.toString().trim()

            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

                if (it == null) {
                    lblStatus.setTextColor(ContextCompat.getColor(this,R.color.red))
                    lblStatus.text = "Data Not Found"
                } else {
                    lblStatus.setTextColor(ContextCompat.getColor(this,R.color.green))
                    lblStatus.text = "Data Found Successfully"

                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)

                }
            })
        }

        btnSaveAndLogin.setOnClickListener {
            strUsername = txtUsername.text.toString().trim()

            loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

                if (it == null) {
                    lblStatus.setTextColor(ContextCompat.getColor(this,R.color.red))
                    lblStatus.text = "Data Not Found"

                } else {
                    lblStatus.setTextColor(ContextCompat.getColor(this,R.color.green))
                    lblStatus.text = "Data Found Successfully"

                    UserStore.deleteAll()

                    UserStore.username = strUsername
                    UserStore.password = strPassword

                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if (UserStore.isUserLoggedIn) {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}