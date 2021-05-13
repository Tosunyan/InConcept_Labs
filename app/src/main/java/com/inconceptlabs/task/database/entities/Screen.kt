package com.inconceptlabs.task.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Screen(
    var name: String = "",
    var backgroundColor: String = "",
    var contentDescription: String = "",
    var enabled: Boolean = false,
    var type: String = "",
    var content: List<Item>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var screenID: Int = 1
}