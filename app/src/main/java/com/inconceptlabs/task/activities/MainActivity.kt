package com.inconceptlabs.task.activities

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.inconceptlabs.task.R
import com.inconceptlabs.task.models.Item
import com.inconceptlabs.task.models.Screen
import com.inconceptlabs.task.utility.*
import com.inconceptlabs.task.viewmodels.ViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkSplashScreen()

        val prefs = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val previouslyStarted = prefs.getBoolean(IS_FIRST_TIME, false)

        if (!previouslyStarted) {
            val edit = prefs.edit()
            edit.putBoolean(IS_FIRST_TIME, true)
            edit.apply()
            storeInLocalDatabase()
        }
    }

    private fun checkSplashScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val graph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)

        if (getJsonObject(navHostFragment.requireContext(), 0).getBoolean(ENABLED))
            graph.startDestination = R.id.splashFragment
        else graph.startDestination = R.id.mainFragment

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }

    private fun storeInLocalDatabase() {
        val screenArray = getJsonArray(applicationContext)
        val itemArray =
            getJsonObject(applicationContext, 1).getJSONObject(CONTENT).getJSONArray(ITEMS)

//        for (i in 0 until itemArray.length()) {
//            Coroutines.io {
//                viewModel.insertItem(
//                    Item(
//                        itemArray.getString(i),
//                        itemArray.getString(i),
//                        itemArray.getString(i)
//                    )
//                )
//            }
//        }
//
//        for (i in 0 until screenArray.length())
//            Coroutines.io {
//                viewModel.insertScreen(
//                    Screen(
//                        screenArray.getString(i),
//                        screenArray.getBoolean(i),
//                        screenArray.getString(i),
//                        screenArray.getString(i),
//
//                        )
//                )
//            }
    }
}