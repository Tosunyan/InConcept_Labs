package com.inconceptlabs.task.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inconceptlabs.task.data.entities.Screen

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScreen(screen: Screen)

    @Query("SELECT * FROM Screen WHERE NAME = :name")
    fun getScreenWithName(name: String): LiveData<Screen>
}