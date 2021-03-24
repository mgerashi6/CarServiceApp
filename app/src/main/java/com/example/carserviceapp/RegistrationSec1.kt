package com.example.carserviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_registration_section_1.*

val dropDownList = arrayOf("Male","Female")
var selectedGender = "";
class RegistrationSec1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_section_1)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        genderSelect.adapter = adapter
        genderSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(genderSelect.selectedItemPosition ==0){
                    condition1()
                }
                if(genderSelect.selectedItemPosition==1){
                    selectedGender = "Male"
                }
                if(genderSelect.selectedItemPosition==2){
                    selectedGender = "Female"
                }
            }
        }



    }

    private fun condition1(){
        Toast.makeText(this,"Please select Gender", Toast.LENGTH_LONG).show()
    }

}