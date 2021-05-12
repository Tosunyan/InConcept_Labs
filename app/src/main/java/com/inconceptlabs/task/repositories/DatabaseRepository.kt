package com.inconceptlabs.task.repositories

import android.content.Context
import com.inconceptlabs.task.database.MyDatabase
import com.inconceptlabs.task.database.entities.Item
import com.inconceptlabs.task.database.entities.Screen

class DatabaseRepository(private val context: Context) {

    private val myDao = MyDatabase.invoke(context).dao()


    suspend fun insertScreen(screen: Screen) = myDao.insertScreen(screen)

    suspend fun insertItem(item: Item) = myDao.insertItem(item)

    fun getAllScreens() = myDao.getAllScreens()

    fun getAllItems() = myDao.getAllItems()
}