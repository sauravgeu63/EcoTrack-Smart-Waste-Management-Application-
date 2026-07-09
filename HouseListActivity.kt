package com.saurav.ecotrack.activities

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.saurav.ecotrack.R
import com.saurav.ecotrack.adapters.HouseAdapter
import com.saurav.ecotrack.models.House

class HouseListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyState: LinearLayout
    private lateinit var houseAdapter: HouseAdapter
    private lateinit var houseList: ArrayList<House>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_list)

        // Initialize Views
        recyclerView = findViewById(R.id.recyclerHouses)
        emptyState = findViewById(R.id.emptyState)

        // Initialize List
        houseList = ArrayList()

        // Setup Adapter
        houseAdapter = HouseAdapter(houseList)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = houseAdapter

        // Firebase Reference
        database = FirebaseDatabase.getInstance().getReference("houses")

        // Fetch Data from Firebase
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                houseList.clear()

                for (data in snapshot.children) {

                    val house = data.getValue(House::class.java)

                    if (house != null) {
                        houseList.add(house)
                    }
                }

                houseAdapter.notifyDataSetChanged()

                // Show Empty State
                if (houseList.isEmpty()) {
                    emptyState.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    emptyState.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
