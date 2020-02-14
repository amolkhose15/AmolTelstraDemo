package com.telstra.amolassignmenttestra.room

import androidx.room.*

@Dao
interface AppDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(app: List<AppEntity>)

    @Query(value = "Select * from AppEntity")
    fun getallData(): List<AppEntity>

    @Query("DELETE FROM AppENTITY")
    fun delete()

    @Update
    fun update(item: List<AppEntity>)



}