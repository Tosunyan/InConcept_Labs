package com.inconceptlabs.task.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inconceptlabs.task.data.entities.Screen
import com.inconceptlabs.task.utility.DATABASE_NAME

@Database(entities = [Screen::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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