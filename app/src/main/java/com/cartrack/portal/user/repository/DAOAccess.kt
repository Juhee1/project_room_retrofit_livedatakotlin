package com.cartrack.portal.user.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginTableModel: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username ")
    fun getLoginDetails(username: String?) : LiveData<LoginTableModel>

}