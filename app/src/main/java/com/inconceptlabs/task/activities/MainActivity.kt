package com.inconceptlabs.task.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import com.inconceptlabs.task.R
import com.inconceptlabs.task.database.entities.Item
import com.inconceptlabs.task.database.entities.Screen
import com.inconceptlabs.task.utility.*
import com.inconceptlabs.task.viewmodels.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        if (!prefs.getBoolean(IS_FIRST_TIME, false)) {
            prefs.edit().putBoolean(IS_FIRST_TIME, true).apply()
            storeInLocalDatabase()
        }
        checkSplashScreen()
    }

    private fun checkSplashScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val graph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)

        viewModel.getAllScreens().observe(this@MainActivity, { screens ->
            if (screens[0].enabled)
                graph.startDestination = R.id.splashFragment
            else graph.startDestination = R.id.mainFragment

            navHostFragment.navController.setGraph(graph, intent.extras)
        })
    }

    private fun storeInLocalDatabase() {
        val json = applicationContext.resources
            .openRawResource(R.raw.sample_navigation)
            .bufferedReader().use { it.readText() }

        val screenArray = JSONObject(json).getJSONArray(SCREENS)
        val itemArray = screenArray
            .getJSONObject(1)
            .getJSONObject(CONTENT)
            .getJSONArray(ITEMS)

        for (i in 0 until itemArray.length()) Coroutines.io {
            viewModel.insertItem(
                Item(
                    itemArray.getJSONObject(i).getString(TITLE),
                    itemArray.getJSONObject(i).getString(NAVIGATE_TO),
                    itemArray.getJSONObject(i).getString(DESCRIPTION)
                )
            )
        }

        viewModel.getAllItems().observe(this@MainActivity, { items ->
            for (i in 0 until screenArray.length()) Coroutines.io {
                viewModel.insertScreen(
                    Screen(
                        screenArray.getJSONObject(i).getString(NAME),
                        screenArray.getJSONObject(i).getString(BACKGROUND_COLOR),
                        screenArray.getJSONObject(i).getString(CONTENT_DESCRIPTION),
                        screenArray.getJSONObject(i).getBoolean(ENABLED),
                        screenArray.getJSONObject(i).getString(TYPE),
                        items
                    )
                )
            }
        })
    }
}