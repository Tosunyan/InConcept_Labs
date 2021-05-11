package com.inconceptlabs.task.utility

import android.content.Context
import com.inconceptlabs.task.R
import org.json.JSONArray
import org.json.JSONObject

fun getJsonObject(context: Context, index: Int): JSONObject {
    val json = context.resources.openRawResource(R.raw.sample_navigation)
        .bufferedReader().use { it.readText() }

    return JSONObject(json)
        .getJSONArray(SCREENS)
        .getJSONObject(index)
}

fun getJsonArray(context: Context): JSONArray {
    val json = context.resources.openRawResource(R.raw.sample_navigation)
        .bufferedReader().use { it.readText() }

    return JSONObject(json)
        .getJSONArray(SCREENS)
}

const val DATABASE_NAME = "MyDatabase"
const val IS_FIRST_TIME = "isFirstTime"

const val SCREENS = "screens"
const val NAME = "name"
const val TITLE = "title"
const val CONTENT = "content"
const val ITEMS = "items"
const val DESCRIPTION = "description"
const val NAVIGATE_TO = "navigateTo"
const val ENABLED = "enabled"
const val CONTENT_DESCRIPTION = "contentDescription"
const val BACKGROUND_COLOR = "backgroundColor"