package com.inconceptlabs.task.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.R
import com.inconceptlabs.task.adapters.DestinationAdapter
import com.inconceptlabs.task.adapters.DestinationAdapter.ItemClickListener
import com.inconceptlabs.task.fragments.MainFragmentDirections.fromMainToDestination
import com.inconceptlabs.task.utility.*
import com.inconceptlabs.task.viewmodels.ViewModel

class MainFragment : Fragment(R.layout.fragment_main), ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<ViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAffinity()
                }
            })
    }

    private fun init(view: View) = view.apply {
        recyclerView = findViewById(R.id.destination_list)

        viewModel.getAllScreens().observe(viewLifecycleOwner, { screens ->
            activity?.title = screens[1].name
            recyclerView.setBackgroundColor(parseColor("#" + screens[1].backgroundColor))

            recyclerView.adapter = DestinationAdapter(screens[1].content!!, this@MainFragment)
            recyclerView.layoutManager = if (screens[1].type == "list")
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            else GridLayoutManager(context, 2)
        })
    }

    override fun onItemClick(destination: String) {
        when (destination) {
            "destination1" -> findNavController(recyclerView).navigate(fromMainToDestination(2))
            "destination2" -> findNavController(recyclerView).navigate(fromMainToDestination(3))
            "destination3" -> findNavController(recyclerView).navigate(fromMainToDestination(4))
            "destination4" -> findNavController(recyclerView).navigate(fromMainToDestination(5))
            "destination5" -> findNavController(recyclerView).navigate(fromMainToDestination(6))
            "destination6" -> findNavController(recyclerView).navigate(fromMainToDestination(7))
        }
    }
}