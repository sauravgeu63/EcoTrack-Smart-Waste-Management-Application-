package com.saurav.ecotrack.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.saurav.ecotrack.R
import com.saurav.ecotrack.models.WasteRecord
import java.text.SimpleDateFormat
import java.util.*

class WasteTrackingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waste_tracking)

        val database = FirebaseDatabase.getInstance().getReference("wasteRecords")

        // Set up waste type dropdown
        val spinner = findViewById<Spinner>(R.id.spinnerWasteType)
        val wasteTypes = listOf("Dry Waste", "Wet Waste", "Recyclable")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wasteTypes)

        val etHouseId     = findViewById<EditText>(R.id.etHouseId)
        val switchCollect = findViewById<Switch>(R.id.switchCollected)
        val btnSave       = findViewById<Button>(R.id.btnSaveWaste)

        btnSave.setOnClickListener {
            val houseId   = etHouseId.text.toString().trim()
            val wasteType = spinner.selectedItem.toString()
            val collected = switchCollect.isChecked
            val date      = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (houseId.isEmpty()) {
                Toast.makeText(this, "⚠️ Please enter House ID!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val recordId = database.push().key ?: return@setOnClickListener
            val record   = WasteRecord(recordId, houseId, date, wasteType, collected)

            database.child(recordId).setValue(record)
                .addOnSuccessListener {
                    Toast.makeText(this, "✅ Record saved!", Toast.LENGTH_SHORT).show()
                    etHouseId.text.clear()
                    switchCollect.isChecked = false
                }
        }
    }
}
