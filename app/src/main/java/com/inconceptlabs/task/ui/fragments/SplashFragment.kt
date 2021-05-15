package com.inconceptlabs.task.ui.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.inconceptlabs.task.R
import com.inconceptlabs.task.ui.fragments.SplashFragmentDirections.fromSplashToMain
import com.inconceptlabs.task.data.viewmodels.ViewModel
import com.inconceptlabs.task.utility.SPLASH

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<ViewModel>()
    private lateinit var textView: AppCompatTextView
    private lateinit var rootLayout: RelativeLayout
    private lateinit var title: AppCompatTextView
    private lateinit var btnBack: AppCompatImageView

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
        title = requireActivity().findViewById(R.id.tv_title)
        btnBack = requireActivity().findViewById(R.id.btn_back)

        viewModel.getScreenWithName(SPLASH).observe(viewLifecycleOwner, { screen ->
            title.text = screen.name
            textView.text = screen.contentDescription
            rootLayout.setBackgroundColor(parseColor("#" + screen.backgroundColor))
        })
    }
}