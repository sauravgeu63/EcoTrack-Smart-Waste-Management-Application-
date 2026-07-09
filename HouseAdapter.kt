package com.saurav.ecotrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saurav.ecotrack.R
import com.saurav.ecotrack.models.House

class HouseAdapter(private val houseList: ArrayList<House>) :
    RecyclerView.Adapter<HouseAdapter.HouseViewHolder>() {

    class HouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val houseName: TextView = itemView.findViewById(R.id.houseName)
        val ownerName: TextView = itemView.findViewById(R.id.ownerName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_house, parent, false)

        return HouseViewHolder(view)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {

        val house = houseList[position]

        holder.houseName.text = house.houseName
        holder.ownerName.text = house.ownerName
    }

    override fun getItemCount(): Int {
        return houseList.size
    }
}
