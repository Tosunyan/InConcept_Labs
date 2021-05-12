package com.inconceptlabs.task.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val title: String,
    val navigateTo: String,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var itemID: Int = 1
}