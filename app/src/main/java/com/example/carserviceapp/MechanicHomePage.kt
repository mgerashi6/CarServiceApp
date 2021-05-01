package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mechanic_home_page.*

class MechanicHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanic_home_page)



        WorkOnCar.setOnClickListener {
            val intent = Intent(this, UpdateCarReportActivity::class.java)
            startActivity(intent)
        }

        MechLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}