package com.inconceptlabs.task.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.inconceptlabs.task.R
import com.inconceptlabs.task.fragments.SplashFragmentDirections.fromSplashToMain
import com.inconceptlabs.task.utility.BACKGROUND_COLOR
import com.inconceptlabs.task.utility.CONTENT_DESCRIPTION
import com.inconceptlabs.task.utility.NAME
import com.inconceptlabs.task.utility.getJsonObject
import java.util.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var textView: AppCompatTextView
    private lateinit var rootLayout: LinearLayoutCompat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        Timer().schedule(object : TimerTask() {
            override fun run() = findNavController(view).navigate(fromSplashToMain())
        }, 1500)
    }

    private fun init(view: View) = view.apply {
        rootLayout = findViewById(R.id.root_layout)
        textView = findViewById(R.id.splash_description)

        val jsonObject = getJsonObject(requireContext(), 0)

        activity?.title = jsonObject.getString(NAME)
        textView.text = jsonObject.getString(CONTENT_DESCRIPTION)
        rootLayout.setBackgroundColor(
            Color.parseColor("#" + jsonObject.getString(BACKGROUND_COLOR))
        )
    }
}