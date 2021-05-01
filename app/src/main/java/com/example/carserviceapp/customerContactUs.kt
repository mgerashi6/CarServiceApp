package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carserviceapp.fragments.CustomerHomeFragment
import kotlinx.android.synthetic.main.activity_customer_car_report.*
import kotlinx.android.synthetic.main.activity_customer_contact_us.*

class customerContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_contact_us)

        backMenu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}