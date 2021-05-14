package com.inconceptlabs.task.utility

import android.content.Context
import com.inconceptlabs.task.R
import com.inconceptlabs.task.data.entities.Item
import org.json.JSONArray
import org.json.JSONObject

const val DATABASE_NAME = "MyDatabase.db"
const val IS_FIRST_TIME = "isFirstTime"

const val SCREENS = "screens"
const val ITEMS = "items"

const val NAME = "name"
const val BACKGROUND_COLOR = "backgroundColor"
const val CONTENT_DESCRIPTION = "contentDescription"
const val ENABLED = "enabled"
const val TYPE = "type"
const val CONTENT = "content"

const val TITLE = "title"
const val NAVIGATE_TO = "navigateTo"
const val DESCRIPTION = "description"

const val MAIN = "Main"
const val SPLASH = "Splash screen"
const val DESTINATION_1 = "Destination 1"
const val DESTINATION_2 = "Destination 2"
const val DESTINATION_3 = "Destination 3"
const val DESTINATION_4 = "Destination 4"
const val DESTINATION_5 = "Destination 5"
const val DESTINATION_6 = "Destination 6"

fun getScreensFromJSON(context: Context): JSONArray {
    val json = context.resources
        .openRawResource(R.raw.sample_navigation)
        .bufferedReader().use { it.readText() }

    return JSONObject(json).getJSONArray(SCREENS)
}

fun getItemsFromJsonAndAddToList(context: Context): List<Item> {
    val itemArray = getScreensFromJSON(context)
        .getJSONObject(1)
        .getJSONObject(CONTENT)
        .getJSONArray(ITEMS)
    val items = ArrayList<Item>()

    for (i in 0 until itemArray.length())
        items.add(Item(
            itemArray.getJSONObject(i).getString(TITLE),
            itemArray.getJSONObject(i).getString(NAVIGATE_TO),
            itemArray.getJSONObject(i).getString(DESCRIPTION)
        ))

    return items
}