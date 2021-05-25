package com.cartrack.portal.user.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Login", indices = [Index(value = arrayOf("username"), unique = true)])
data class LoginTableModel (

    @ColumnInfo(name = "username")
    var Username: String,

    @ColumnInfo(name = "password")
    var Password: String

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}