package com.cartrack.portal.user.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cartrack.portal.user.Constant
import com.cartrack.portal.user.R
import com.cartrack.portal.user.network.UserDetail


class UserRecyclerAdapter(var userDetailArrayList: ArrayList<UserDetail>): RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    var itemList = userDetailArrayList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNameText: TextView

        init {
            itemNameText = itemView.findViewById(R.id.text_name)

            itemView.setOnClickListener {
                var position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, UserDetailActivity::class.java).apply {
                    putExtra(Constant.EXTRA_LATITUDE, itemList[position].address.geo.lat)
                    putExtra(Constant.EXTRA_LONGITUDE, itemList[position].address.geo.lng)
                    putExtra(Constant.EXTRA_NAME, itemList[position].username)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_items, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemNameText.text = itemList.get(i).username
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}