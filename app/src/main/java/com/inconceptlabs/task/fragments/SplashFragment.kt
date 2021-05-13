package com.inconceptlabs.task.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.inconceptlabs.task.R
import com.inconceptlabs.task.fragments.SplashFragmentDirections.fromSplashToMain
import com.inconceptlabs.task.viewmodels.ViewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var textView: AppCompatTextView
    private lateinit var rootLayout: RelativeLayout
    private val viewModel by viewModels<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController(view).navigate(fromSplashToMain())
        }, 1000)
    }

    private fun init(view: View) = view.apply {
        rootLayout = findViewById(R.id.root_layout)
        textView = findViewById(R.id.splash_description)

        viewModel.getAllScreens().observe(viewLifecycleOwner, { screens ->
            activity?.title = screens[0].name
            textView.text = screens[0].contentDescription
            rootLayout.setBackgroundColor(parseColor("#" + screens[0].backgroundColor))
        })
    }
}