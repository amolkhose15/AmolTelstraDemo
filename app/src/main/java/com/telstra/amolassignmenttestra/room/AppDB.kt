package com.telstra.amolassignmenttestra.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(AppEntity::class)], version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun appdeo(): AppDAO


}