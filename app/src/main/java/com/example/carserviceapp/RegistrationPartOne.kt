package com.example.carserviceapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration_part_one.*


val dropDownListGender = arrayOf("Gender", "Male","Female")
val dropDownListCountry = arrayOf("Country", "Qatar")
val dropDownListCity = arrayOf("City", "Doha")

class RegistrationPartOne : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_part_one)

        var selectedGender = ""
        var selectedCountry = ""
        var selectedCity = ""
        val firstName = findViewById<EditText>(R.id.first_name).text
        val lastName = findViewById<EditText>(R.id.last_name).text
        val mobile = findViewById<EditText>(R.id.phone).text
        val nationalID = findViewById<EditText>(R.id.phone).text
        val dob = findViewById<EditText>(R.id.dob).text
        val email = findViewById<EditText>(R.id.email).text
        val password = findViewById<EditText>(R.id.password).text


        //Gender Select
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, dropDownListGender)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        genderSelect.adapter = adapter
        genderSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(genderSelect.selectedItemPosition ==0){
                    condition1()
                }
                if(genderSelect.selectedItemPosition== 1){
                    selectedGender = "Male"
                }
                if(genderSelect.selectedItemPosition== 2){
                    selectedGender = "Female"
                }
            }
        }

        //Country Select
        val adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_item, dropDownListCountry)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)
        countrySelect.adapter = adapter1
        countrySelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(countrySelect.selectedItemPosition == 0){
                    condition1()
                }
                if(countrySelect.selectedItemPosition== 1){
                    selectedCountry = "Qatar"
                }
            }
        }


        //City Select
        val adapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item, dropDownListCity)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item)
        citySelect.adapter = adapter2
        citySelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(citySelect.selectedItemPosition == 0){
                    condition1()
                }
                if(citySelect.selectedItemPosition== 1){
                    selectedCity = "Doha"
                }
            }
        }


        button.setOnClickListener{
            val checkedButton = checkLegal.isChecked

            if(!checkedButton){
                Toast.makeText(this,"Please accept the terms", Toast.LENGTH_SHORT).show()

            }

            var valid = Validation().validation(firstName, lastName, mobile, nationalID, email)

            if(valid && checkedButton){
                //Call DB
                var helper = DBHelper(applicationContext)
                var db = helper.readableDatabase
                //EncryptPass
                var encryptedPass = AESEncryption.encrypt(password.toString())
                var newPass = encryptedPass.toString()
                //Ready sql statement and insert
                var cv = RegisterDB().enterRegistrationDB(firstName,lastName,selectedGender,selectedCountry,selectedCity,mobile,nationalID,dob,email,newPass)
                db.insert("CUSTOMER",null,cv)

                val intent = Intent(this, loginPage::class.java)
                startActivity(intent)

//                intent.putExtra("firstName", firstName)
//                intent.putExtra("lastName", lastName)
//                intent.putExtra("gender", selectedGender)
//                intent.putExtra("country", selectedCountry)
//                intent.putExtra("city", selectedCity)
//                intent.putExtra("mobile", mobile)
//                intent.putExtra("nationalID", nationalID)
//                intent.putExtra("dob", dob)
//                intent.putExtra("email", email)
//                intent.putExtra("password", password)


            }

        }


    }



    private fun condition1(){
        Toast.makeText(this,"Please select a correct option", Toast.LENGTH_SHORT).show()
    }






}