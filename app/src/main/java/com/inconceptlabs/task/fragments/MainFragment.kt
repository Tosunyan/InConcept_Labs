package com.inconceptlabs.task.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.R
import com.inconceptlabs.task.adapters.DestinationAdapter
import com.inconceptlabs.task.adapters.DestinationAdapter.ItemClickListener
import com.inconceptlabs.task.fragments.MainFragmentDirections.fromMainToDestination
import com.inconceptlabs.task.utility.*
import org.json.JSONObject

class MainFragment : Fragment(R.layout.fragment_main), ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var jsonObject: JSONObject

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAffinity()
                }
            })
    }

    private fun init(view: View) = view.apply {
        recyclerView = findViewById(R.id.destination_list)
        jsonObject = getJsonObject(requireContext(), 1)

        activity?.title = jsonObject.getString(NAME)
        recyclerView.setBackgroundColor(Color.parseColor("#" + jsonObject.getString(BACKGROUND_COLOR)))

        recyclerView.adapter = DestinationAdapter(addToArrayList(), this@MainFragment)
        recyclerView.layoutManager = if (jsonObject.getString("type") == "list")
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        else GridLayoutManager(context, 2)
    }

    private fun addToArrayList(): ArrayList<Any> {
        val list = ArrayList<Any>()

        for (i in 0 until jsonObject.getJSONObject(CONTENT).getJSONArray(ITEMS).length())
            list.add(jsonObject.getJSONObject(CONTENT).getJSONArray(ITEMS).get(i))

        return list
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