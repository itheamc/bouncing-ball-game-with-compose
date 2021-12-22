package com.itheamc.bouncingball.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val _id: Int = 0,
    @ColumnInfo(name = "score") val _score: Int,
    @ColumnInfo(name = "scored_on") val _date: Long = Date().time
)
