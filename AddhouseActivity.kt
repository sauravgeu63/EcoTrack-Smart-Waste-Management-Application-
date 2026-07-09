package com.saurav.ecotrack.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.saurav.ecotrack.R
import com.saurav.ecotrack.models.House

class AddHouseActivity : AppCompatActivity() {

    private lateinit var etHouseName: TextInputEditText
    private lateinit var etOwnerName: TextInputEditText
    private lateinit var actvPaymentStatus: MaterialAutoCompleteTextView
    private lateinit var btnAddHouse: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_house)

        etHouseName = findViewById(R.id.etHouseName)
        etOwnerName = findViewById(R.id.etOwnerName)
        actvPaymentStatus = findViewById(R.id.actvPaymentStatus)
        btnAddHouse = findViewById(R.id.btnAddHouse)

        setupPaymentStatusDropdown()
        setupAddButton()
    }

    private fun setupPaymentStatusDropdown() {
        val statuses = listOf("Paid", "Unpaid", "Pending", "Partially Paid")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            statuses
        )
        actvPaymentStatus.setAdapter(adapter)
    }

    private fun setupAddButton() {
        btnAddHouse.setOnClickListener {
            val houseName = etHouseName.text.toString().trim()
            val ownerName = etOwnerName.text.toString().trim()
            val paymentStatus = actvPaymentStatus.text.toString().trim()

            if (!validateInputs(houseName, ownerName, paymentStatus)) return@setOnClickListener

            val house = House(
                houseName = houseName,
                ownerName = ownerName,
                paymentStatus = paymentStatus
            )

            // TODO: Save to Firebase or Room DB
            Toast.makeText(this, "House '${house.houseName}' added!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun validateInputs(
        houseName: String,
        ownerName: String,
        paymentStatus: String
    ): Boolean {
        if (houseName.isEmpty()) {
            etHouseName.error = "House name is required"
            etHouseName.requestFocus()
            return false
        }
        if (ownerName.isEmpty()) {
            etOwnerName.error = "Owner name is required"
            etOwnerName.requestFocus()
            return false
        }
        if (paymentStatus.isEmpty()) {
            Toast.makeText(this, "Please select a payment status", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
