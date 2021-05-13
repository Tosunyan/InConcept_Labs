package com.inconceptlabs.task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.inconceptlabs.task.R
import com.inconceptlabs.task.adapters.DestinationAdapter.DestinationViewHolder
import com.inconceptlabs.task.database.entities.Item

class DestinationAdapter(
    private val items: List<Item>,
    val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<DestinationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder =
        DestinationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.destination_item, parent, false)
        )

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.description.text = items[position].description
    }

    override fun getItemCount() = items.size

    inner class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.item_title)
        val description: AppCompatTextView = itemView.findViewById(R.id.item_description)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(items[adapterPosition].navigateTo)
                itemView.performHapticFeedback(1)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(destination: String)
    }
}