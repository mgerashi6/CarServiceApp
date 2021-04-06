package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration_part_one.*
import kotlinx.android.synthetic.main.activity_registration_part_two.*


class RegistrationPartTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_part_two)


        val selectCar = "# of Cars"

        val dropDownListCar = mutableListOf<Any>()
        dropDownListCar.add(selectCar)
        for(i in 1..30){
            dropDownListCar.add(i)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownListCar)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        carAmount.adapter = adapter
        carAmount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(carAmount.selectedItemPosition == 0){
                    condition1()
                }
            }
        }


        backToRegister.setOnClickListener{

            val intent = Intent(this, RegistrationPartOne::class.java)
            startActivity(intent)


        }

        nextButton.setOnClickListener{
            val ans = carAmount.selectedItem.toString()
            Toast.makeText(this,ans, Toast.LENGTH_LONG).show()

            val intent = Intent(this, RegistrationPartThree::class.java)
            startActivity(intent)


        }






    }
    private fun condition1(){
        Toast.makeText(this,"Please select a correct option", Toast.LENGTH_LONG).show()
    }
}