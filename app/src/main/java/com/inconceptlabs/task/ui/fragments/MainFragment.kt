package com.inconceptlabs.task.ui.fragments

import android.graphics.Color.parseColor
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.R
import com.inconceptlabs.task.data.adapters.DestinationAdapter
import com.inconceptlabs.task.data.adapters.DestinationAdapter.ItemClickListener
import com.inconceptlabs.task.data.viewmodels.ViewModel
import com.inconceptlabs.task.ui.fragments.MainFragmentDirections.fromMainToDestination
import com.inconceptlabs.task.utility.*

class MainFragment : Fragment(R.layout.fragment_main), ItemClickListener {

    private val viewModel by viewModels<ViewModel>()
    private lateinit var title: AppCompatTextView
    private lateinit var btnBack: AppCompatImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DestinationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        onClick()
    }

    private fun init(view: View) = view.apply {
        recyclerView = findViewById(R.id.destination_list)
        btnBack = requireActivity().findViewById(R.id.btn_back)
        title = requireActivity().findViewById(R.id.tv_title)

        btnBack.visibility = View.VISIBLE

        viewModel.getScreenWithName(MAIN).observe(viewLifecycleOwner, { screen ->
            title.text = screen.name
            recyclerView.setBackgroundColor(parseColor("#" + screen.backgroundColor))

            adapter = DestinationAdapter(screen.content!!.items, this@MainFragment)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = if (screen.type == "list")
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            else GridLayoutManager(context, 2)
        })
    }

    override fun onItemClick(destination: String) {
        when (destination) {
            "destination1" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_1))
            "destination2" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_2))
            "destination3" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_3))
            "destination4" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_4))
            "destination5" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_5))
            "destination6" -> findNavController(recyclerView).navigate(fromMainToDestination(DESTINATION_6))
        }
    }

    private fun onClick() {
        btnBack.setOnClickListener { activity?.finishAffinity() }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAffinity()
                }
            })
    }
}