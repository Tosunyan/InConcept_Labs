package com.inconceptlabs.task.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.inconceptlabs.task.data.entities.Content
import com.inconceptlabs.task.data.entities.Item
import com.inconceptlabs.task.data.entities.Screen
import com.inconceptlabs.task.data.repositories.DatabaseRepository
import com.inconceptlabs.task.utility.*

class ViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repository = DatabaseRepository(app)
    private val screenArray = getScreensFromJSON(app.baseContext)

    fun getScreenWithName(name: String) = repository.getScreenWithName(name)

    fun storeInLocalDatabase() {
        for (i in 0 until screenArray.length()) Coroutines.io {
            repository.insertScreen(Screen(
                screenArray.getJSONObject(i).getString(NAME),
                screenArray.getJSONObject(i).getString(BACKGROUND_COLOR),
                if (i != 1) screenArray.getJSONObject(i).getString(CONTENT_DESCRIPTION) else "",
                if (i == 0) screenArray.getJSONObject(i).getBoolean(ENABLED) else false,
                if (i == 1) screenArray.getJSONObject(i).getString(TYPE) else "",
                if (i == 1) Content(getItemsFromJsonAndAddToList(app.baseContext)) else null
            ))
        }
    }
}