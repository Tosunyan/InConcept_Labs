package com.inconceptlabs.task.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inconceptlabs.task.models.Item
import com.inconceptlabs.task.models.Screen
import com.inconceptlabs.task.utility.DATABASE_NAME

@Database(entities = [Screen::class, Item::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dao(): MyDao

    companion object {
        private var database: MyDatabase? = null

        operator fun invoke(context: Context): MyDatabase {
            if (database == null)
                database = Room
                    .databaseBuilder(context, MyDatabase::class.java, DATABASE_NAME)
                    .build()
            return database!!
        }
    }
}