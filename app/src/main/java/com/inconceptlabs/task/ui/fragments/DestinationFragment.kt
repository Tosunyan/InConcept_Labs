package com.inconceptlabs.task.ui.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.inconceptlabs.task.R
import com.inconceptlabs.task.data.viewmodels.ViewModel

class DestinationFragment : Fragment(R.layout.fragment_destination) {

    private val viewModel by viewModels<ViewModel>()
    private lateinit var description: AppCompatTextView
    private lateinit var title: AppCompatTextView
    private lateinit var icBack: AppCompatImageView
    private lateinit var layout: RelativeLayout
    private lateinit var name: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) = view.apply {
        description = findViewById(R.id.tv_destination)
        layout = findViewById(R.id.destination_root)
        title = findViewById(R.id.destination_title)
        icBack = findViewById(R.id.ic_back)

        icBack.setOnClickListener {
            Navigation.findNavController(view).navigateUp()
            performHapticFeedback(1)
        }

        name = DestinationFragmentArgs.fromBundle(requireArguments()).name

        viewModel.getScreenWithName(name).observe(viewLifecycleOwner, { screen ->
            title.text = screen.name
            description.text = screen.contentDescription
            layout.setBackgroundColor(parseColor("#" + screen.backgroundColor))
        })
    }
}