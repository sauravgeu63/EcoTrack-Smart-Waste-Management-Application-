# EcoTrack-Smart-Waste-Management-Application-
EcoTrack is an Android-based application designed to efficiently manage waste collection in residential colonies. It enables tracking of daily waste collection, categorization of waste types, and management of monthly collection fees with real-time data storage using Firebase. 


##MAIN ACTIVIT.KT code
package com.saurav.ecotrack.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.saurav.ecotrack.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // When House button clicked → go to House screen
        findViewById<Button>(R.id.btnHouses).setOnClickListener {
            startActivity(Intent(this, HouseListActivity::class.java))
        }

        // When Waste button clicked → go to Waste Tracking screen
        findViewById<Button>(R.id.btnWaste).setOnClickListener {
            startActivity(Intent(this, WasteTrackingActivity::class.java))
        }

        // When Fee button clicked → go to Fee screen
        findViewById<Button>(R.id.btnFees).setOnClickListener {
            startActivity(Intent(this, FeeManagementActivity::class.java))
        }

        // When Reports button clicked → go to Reports screen
        findViewById<Button>(R.id.btnReports).setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }
    }
}
