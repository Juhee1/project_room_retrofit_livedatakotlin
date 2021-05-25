package com.cartrack.portal.user.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cartrack.portal.user.R
import com.cartrack.portal.user.userstore.UserStore

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        if (savedInstanceState == null) {
            val fragment = UserListFragment.newInstance()
            loadFragment(fragment)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        UserStore.deleteAll()
        finish()
    }
}