package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cs_home_page.*

class cs_home_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_home_page)

        HPSearch.setOnClickListener {
            val intent = Intent(this, cs_car_plate_search::class.java)
            startActivity(intent)
        }

        HPTodaysAppointment.setOnClickListener {
            val intent = Intent(this, Today_AppointmentsActivity::class.java)
            startActivity(intent)
        }
    }
}