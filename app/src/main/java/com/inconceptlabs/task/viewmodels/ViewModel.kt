package com.inconceptlabs.task.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.inconceptlabs.task.database.entities.Item
import com.inconceptlabs.task.database.entities.Screen
import com.inconceptlabs.task.repositories.DatabaseRepository
import com.inconceptlabs.task.utility.*

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DatabaseRepository(application)

    fun getAllScreens() = repository.getAllScreens()

    fun getAllItems() = repository.getAllItems()

    suspend fun insertScreen(screen: Screen) = repository.insertScreen(screen)

    suspend fun insertItem(item: Item) = repository.insertItem(item)
}