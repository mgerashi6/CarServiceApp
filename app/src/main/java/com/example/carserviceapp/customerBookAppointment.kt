package com.example.carserviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carserviceapp.fragments.CustomerCallFragment
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.fragments.CustomerProfileFragment
import kotlinx.android.synthetic.main.activity_customer_home_page.*

class customerBookAppointment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_book_appointment)


    }


}