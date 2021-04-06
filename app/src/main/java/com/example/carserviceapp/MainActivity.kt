package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_book_appointment)

        existing_customer.setOnClickListener {
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }

        new_customer.setOnClickListener{
            val intent = Intent(this, RegistrationPartOne::class.java)
            startActivity(intent)
        }

    }

}





