package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registration_part_two.*

class RegistrationPartThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_part_three)










        backToRegister.setOnClickListener{

            val intent = Intent(this, RegistrationPartTwo::class.java)
            startActivity(intent)


        }
    }
}