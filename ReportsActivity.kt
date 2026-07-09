package com.saurav.ecotrack.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.saurav.ecotrack.R
import com.saurav.ecotrack.models.House

class ReportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        val database    = FirebaseDatabase.getInstance().getReference("houses")
        val tvTotal     = findViewById<TextView>(R.id.tvTotalHouses)
        val tvPaid      = findViewById<TextView>(R.id.tvTotalPaid)
        val tvPending   = findViewById<TextView>(R.id.tvTotalPending)
        val btnLoad     = findViewById<Button>(R.id.btnLoadReport)

        btnLoad.setOnClickListener {
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var total = 0; var paid = 0; var pending = 0
                    for (data in snapshot.children) {
                        val house = data.getValue(House::class.java)
                        if (house != null) {
                            total++
                            if (house.paymentStatus == "Paid") paid++ else pending++
                        }
                    }
                    tvTotal.text   = "🏠 Total Houses: $total"
                    tvPaid.text    = "✅ Paid: $paid"
                    tvPending.text = "⏳ Pending: $pending"
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}
