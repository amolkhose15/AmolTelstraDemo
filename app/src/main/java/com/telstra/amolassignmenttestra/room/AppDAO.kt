package com.telstra.amolassignmenttestra.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBooks(app: AppEntity)

    @Query(value = "Select * from AppEntity")
    fun getAllBooks(): List<AppEntity>

    @Query("DELETE FROM AppENTITY")
    fun delete()
}