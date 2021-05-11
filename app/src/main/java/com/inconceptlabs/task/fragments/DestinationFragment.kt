package com.inconceptlabs.task.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.inconceptlabs.task.R
import com.inconceptlabs.task.utility.BACKGROUND_COLOR
import com.inconceptlabs.task.utility.CONTENT_DESCRIPTION
import com.inconceptlabs.task.utility.NAME
import com.inconceptlabs.task.utility.getJsonObject
import org.json.JSONObject

class DestinationFragment : Fragment(R.layout.fragment_destination) {

    private lateinit var description: AppCompatTextView
    private lateinit var layout: RelativeLayout
    private lateinit var jsonObject: JSONObject
    private var index: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

    private fun init(view: View) = view.apply {
        description = findViewById(R.id.tv_destination)
        layout = findViewById(R.id.destination_root)

        index = DestinationFragmentArgs.fromBundle(requireArguments()).index
        jsonObject = getJsonObject(context, index)

        activity?.title = jsonObject.getString(NAME)
        description.text = jsonObject.getString(CONTENT_DESCRIPTION)
        layout.setBackgroundColor(Color.parseColor("#" + jsonObject.getString(BACKGROUND_COLOR)))
    }
}