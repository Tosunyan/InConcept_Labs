package com.inconceptlabs.task.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    val title: String,
    val navigateTo: String,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
}