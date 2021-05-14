package com.inconceptlabs.task.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.inconceptlabs.task.data.entities.Item

class Converters {
    @TypeConverter
    fun listToJson(value: List<Item>?): String? =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<Item> =
        Gson().fromJson(value, Array<Item>::class.java).toList()
}