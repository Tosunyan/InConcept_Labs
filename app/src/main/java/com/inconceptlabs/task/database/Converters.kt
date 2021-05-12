package com.inconceptlabs.task.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.inconceptlabs.task.database.entities.Item

class Converters {
    @TypeConverter
    fun listToJson(value: List<Item>): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<Item>::class.java).toList()
}