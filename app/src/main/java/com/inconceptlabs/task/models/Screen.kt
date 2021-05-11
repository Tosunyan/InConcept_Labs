package com.inconceptlabs.task.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inconceptlabs.task.responses.ItemResponse

@Entity
data class Screen(
    val name: String,
    val enabled: Boolean,
    val backgroundColor: String,
    val contentDescription: String,
    val content: ItemResponse
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
}