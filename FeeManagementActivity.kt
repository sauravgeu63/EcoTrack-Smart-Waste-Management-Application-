package com.saurav.ecotrack.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.saurav.ecotrack.R

class FeeManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fee_management)

        val database     = FirebaseDatabase.getInstance().getReference("houses")
        val etHouseId    = findViewById<EditText>(R.id.etHouseIdFee)
        val tvStatus     = findViewById<TextView>(R.id.tvPaymentStatus)
        val btnCheck     = findViewById<Button>(R.id.btnCheckStatus)
        val btnMarkPaid  = findViewById<Button>(R.id.btnMarkPaid)

        // Check payment status
        btnCheck.setOnClickListener {
            val houseId = etHouseId.text.toString().trim()
            if (houseId.isEmpty()) {
                Toast.makeText(this, "Enter House ID first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            database.child(houseId).child("paymentStatus")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val status = snapshot.getValue(String::class.java) ?: "Not Found"
                        tvStatus.text = "Status: $status"
                    }
                    override fun onCancelled(error: DatabaseError) {}
                })
        }

        // Mark as paid
        btnMarkPaid.setOnClickListener {
            val houseId = etHouseId.text.toString().trim()
            if (houseId.isEmpty()) {
                Toast.makeText(this, "Enter House ID first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            database.child(houseId).child("paymentStatus").setValue("Paid")
                .addOnSuccessListener {
                    tvStatus.text = "Status: Paid"
                    Toast.makeText(this, "✅ Marked as Paid!", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
