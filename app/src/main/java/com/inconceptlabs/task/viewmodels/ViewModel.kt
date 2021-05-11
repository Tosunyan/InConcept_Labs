package com.inconceptlabs.task.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.inconceptlabs.task.models.Item
import com.inconceptlabs.task.models.Screen
import com.inconceptlabs.task.repositories.DatabaseRepository

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DatabaseRepository(application)


    suspend fun insertScreen(screen: Screen) = repository.insertScreen(screen)

    suspend fun insertItem(item: Item) = repository.insertItem(item)

    fun getAllScreens() = repository.getAllScreens()

    fun getAllItems() = repository.getAllItems()
}