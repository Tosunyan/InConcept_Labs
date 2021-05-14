package com.inconceptlabs.task.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Screen(
    @PrimaryKey
    val name: String,
    val backgroundColor: String,
    val contentDescription: String,
    val enabled: Boolean,
    val type: String,
    @Embedded var content: Content?
)

data class Content(
    var items: List<Item>
)