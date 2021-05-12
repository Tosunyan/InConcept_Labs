package com.inconceptlabs.task.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Screen(
    val name: String,
    val backgroundColor: String,

    @ColumnInfo(defaultValue = "") val contentDescription: String,

    var enabled: Boolean = false,

    @ColumnInfo(defaultValue = "list") val type: String,

    var content: List<Item>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var screenID: Int = 1
}