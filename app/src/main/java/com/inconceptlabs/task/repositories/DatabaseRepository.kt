package com.inconceptlabs.task.repositories

import android.content.Context
import com.inconceptlabs.task.database.MyDatabase
import com.inconceptlabs.task.models.Item
import com.inconceptlabs.task.models.Screen

class DatabaseRepository(private val context: Context) {

    private val myDao = MyDatabase.invoke(context).dao()


    suspend fun insertScreen(screen: Screen) = myDao.insertScreen(screen)

    suspend fun insertItem(item: Item) = myDao.insertItems(item)

    fun getAllScreens() = myDao.getAllScreens()

    fun getAllItems() = myDao.getAllItems()
}