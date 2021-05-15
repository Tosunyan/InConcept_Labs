package com.inconceptlabs.task.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import com.inconceptlabs.task.R
import com.inconceptlabs.task.data.viewmodels.ViewModel
import com.inconceptlabs.task.utility.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<ViewModel>()
    private lateinit var prefs: SharedPreferences
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var graph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        graph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)

        if (!prefs.getBoolean(IS_FIRST_TIME, false)) {
            prefs.edit().putBoolean(IS_FIRST_TIME, true).apply()
            viewModel.storeInLocalDatabase()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkSplashScreen()
        }, 100)
    }

    private fun checkSplashScreen() =
        viewModel.getScreenWithName(SPLASH).observe(this@MainActivity, { screen ->
            if (screen.enabled)
                graph.startDestination = R.id.splashFragment
            else graph.startDestination = R.id.mainFragment

            navHostFragment.navController.setGraph(graph, intent.extras)
        })
}