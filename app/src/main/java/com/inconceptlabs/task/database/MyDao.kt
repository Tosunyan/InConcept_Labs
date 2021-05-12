package com.inconceptlabs.task.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inconceptlabs.task.database.entities.Item
import com.inconceptlabs.task.database.entities.Screen

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreen(screen: Screen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)


    @Query("SELECT * FROM Screen")
    fun getAllScreens(): LiveData<List<Screen>>

    @Query("SELECT * FROM Item")
    fun getAllItems(): LiveData<List<Item>>
}