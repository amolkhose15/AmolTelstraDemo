package com.telstra.amolassignmenttestra.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.telstra.amolassignmenttestra.R

@Database(entities = [(AppEntity::class)], version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun appdeo(): AppDAO


//    Create a instance for Database Class
    companion object {
        fun getInstance(context: Context): AppDAO =
            Room.databaseBuilder(context.applicationContext, AppDB::class.java,context.getString(R.string.telstra)).allowMainThreadQueries().build().appdeo()
    }

}