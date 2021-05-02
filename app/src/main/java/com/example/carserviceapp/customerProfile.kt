package com.example.carserviceapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_customer_profile.*
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.android.synthetic.main.activity_customer_profile.sign_out as sign_out1
import kotlinx.android.synthetic.main.fragment_customer_profile.change_password as change_password1

class customerProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.carserviceapp.R.layout.activity_customer_profile)

        var customerFirstNameData = ""
        var customerLastNameData = ""
        var customerGenderData = ""
        var customerRegionData = ""
        var customerCityData = ""
        var customerMobilePhoneData = ""
        var customerNationalIdData = ""
        var customerProfilePasswordData = ""

        var customerFirstName = findViewById<EditText>(com.example.carserviceapp.R.id.first_name) as EditText
        var customerLastName = findViewById<EditText>(com.example.carserviceapp.R.id.last_name) as EditText
        var customerGender = findViewById<EditText>(com.example.carserviceapp.R.id.gender) as EditText
        var customerRegion = findViewById<EditText>(com.example.carserviceapp.R.id.region) as EditText
        var customerCity = findViewById<EditText>(com.example.carserviceapp.R.id.city) as EditText
        var customerMobilePhone = findViewById<EditText>(com.example.carserviceapp.R.id.mobile_phone) as EditText
        var customerNationalId = findViewById<EditText>(com.example.carserviceapp.R.id.national_ID) as EditText
        /*var customerProfilePassword = findViewById<EditText>(com.example.carserviceapp.R.id.profile_password) as EditText*/

        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery(
                "SELECT FIRST_NAME, FAMILY_NAME, GENDER, REGION, CITY, MOBILE_PHONE, NATIONAL_ID, PASSWORD FROM CUSTOMER WHERE CUSTOMER_ID = $customerID",
                null
        )
        //Log.d("check", rs.getString(0))
        if(rs.moveToNext()) {
            customerFirstNameData = rs.getString(0)
            customerLastNameData = rs.getString(1)
            customerGenderData = rs.getString(2)
            customerRegionData = rs.getString(3)
            customerCityData = rs.getString(4)
            customerMobilePhoneData = rs.getString(5)
            customerNationalIdData = rs.getString(6)
            //Toast.makeText(applicationContext,rs.getString(0),Toast.LENGTH_SHORT).show()
        }

        customerFirstName.setText(customerFirstNameData)
        customerLastName.setText(customerLastNameData)
        customerGender.setText(customerGenderData)
        customerRegion.setText(customerRegionData)
        customerCity.setText(customerCityData)
        customerMobilePhone.setText(customerMobilePhoneData)
        customerNationalId.setText(customerNationalIdData)
        /*customerProfilePassword.setText(customerProfilePasswordData)*/

        /*
        Updating the profile is currently non-functional.
        change_password.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Password changed", Toast.LENGTH_SHORT).show()
        }*/

        sign_out.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}