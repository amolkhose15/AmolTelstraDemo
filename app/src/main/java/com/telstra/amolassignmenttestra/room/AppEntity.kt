package com.telstra.amolassignmenttestra.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var IDD: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "imageHref")
    var imageHref: String = ""
)