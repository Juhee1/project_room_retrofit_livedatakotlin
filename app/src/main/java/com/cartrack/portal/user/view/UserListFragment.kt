package com.cartrack.portal.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cartrack.portal.user.R
import com.cartrack.portal.user.network.UserDetail
import com.cartrack.portal.user.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*


class UserListFragment : Fragment() {

    private var adapter: RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>? = null
    private var userDetailArrayList: ArrayList<UserDetail>? = null
    lateinit var userListModel: UserListViewModel

    companion object {
        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        userListModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)

            userListModel.loadData()?.observe(this@UserListFragment,
                Observer { list ->
                    processResponse(list)
                })
        }

        btn_logout.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun processResponse(userDetailList: List<UserDetail>) {
        userDetailArrayList = ArrayList(userDetailList)
        adapter = UserRecyclerAdapter(userDetailArrayList!!)
        recycler_view.adapter = adapter
    }
}