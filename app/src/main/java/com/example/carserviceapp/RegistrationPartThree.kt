package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carserviceapp.fragments.CustomerHomeFragment
import kotlinx.android.synthetic.main.activity_registration_part_one.*
import kotlinx.android.synthetic.main.activity_registration_part_one.button
import kotlinx.android.synthetic.main.activity_registration_part_three.*
import kotlinx.android.synthetic.main.activity_registration_part_two.*
import kotlinx.android.synthetic.main.activity_registration_part_two.backToRegister

class RegistrationPartThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_part_three)



        button2.setOnClickListener{

            val intent = Intent(this, customerHomePage::class.java)
            startActivity(intent)


        }






        backToRegister.setOnClickListener{

            val intent = Intent(this, RegistrationPartTwo::class.java)
            startActivity(intent)


        }
    }
}