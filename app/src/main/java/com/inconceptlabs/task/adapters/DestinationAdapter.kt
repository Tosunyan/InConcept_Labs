package com.inconceptlabs.task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.R
import com.inconceptlabs.task.adapters.DestinationAdapter.DestinationViewHolder
import com.inconceptlabs.task.utility.*
import org.json.JSONObject

class DestinationAdapter(
    private val destinations: ArrayList<Any>,
    val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<DestinationViewHolder>() {

    private lateinit var jsonObject: JSONObject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        jsonObject = getJsonObject(parent.context, 1).getJSONObject(CONTENT)
        return DestinationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.destination_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.title.text = jsonObject.getJSONArray(ITEMS).getJSONObject(position).getString(TITLE)
        holder.description.text =
            jsonObject.getJSONArray(ITEMS).getJSONObject(position).getString(DESCRIPTION)
    }

    override fun getItemCount() = destinations.size

    inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.item_title)
        val description: AppCompatTextView = itemView.findViewById(R.id.item_description)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(jsonObject.getJSONArray(ITEMS).getJSONObject(adapterPosition).getString(NAVIGATE_TO))
                itemView.performHapticFeedback(1)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(destination: String)
    }
}