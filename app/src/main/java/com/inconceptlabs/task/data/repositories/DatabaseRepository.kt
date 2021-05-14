package com.inconceptlabs.task.data.repositories

import android.content.Context
import com.inconceptlabs.task.data.database.MyDatabase
import com.inconceptlabs.task.data.entities.Screen

class DatabaseRepository(context: Context) {

    private val myDao = MyDatabase.invoke(context).dao()

    suspend fun insertScreen(screen: Screen) = myDao.insertScreen(screen)

    fun getScreenWithName(name: String) = myDao.getScreenWithName(name)
}