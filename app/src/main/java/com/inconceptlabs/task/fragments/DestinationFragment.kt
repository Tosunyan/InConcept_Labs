package com.inconceptlabs.task.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.inconceptlabs.task.R
import com.inconceptlabs.task.viewmodels.ViewModel

class DestinationFragment : Fragment(R.layout.fragment_destination) {

    private lateinit var description: AppCompatTextView
    private lateinit var layout: RelativeLayout
    private val viewModel by viewModels<ViewModel>()
    private var index: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) = view.apply {
        description = findViewById(R.id.tv_destination)
        layout = findViewById(R.id.destination_root)

        index = DestinationFragmentArgs.fromBundle(requireArguments()).index

        viewModel.getAllScreens().observe(viewLifecycleOwner, { screens ->
            activity?.title = screens[index].name
            description.text = screens[index].contentDescription
            layout.setBackgroundColor(parseColor("#" + screens[index].backgroundColor))
        })
    }
}