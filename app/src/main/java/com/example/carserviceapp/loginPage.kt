package com.example.carserviceapp

import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.activity_registration_part_one.*

var customerID = ""
class loginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)




        signin_button.setOnClickListener {
            var emailInput = findViewById<EditText>(R.id.email_input).text.toString()
            var passGet = findViewById<EditText>(R.id.pass_input).text.toString()


            var customer_email = ""
            var customer_pass = ""
            if(emailInput.isEmpty() || passGet.isEmpty()){
                Toast.makeText(applicationContext,"Please enter email and password", Toast.LENGTH_SHORT).show()

            }
            else{
                try{
                    var helper = DBHelper(applicationContext)
                    var db = helper.readableDatabase
                    var rs = db.rawQuery("SELECT CUSTOMER_ID, EMAIL, PASSWORD FROM CUSTOMER WHERE EMAIL = '$emailInput'", null)
                    //Log.d("check", rs.getString(0))
                    if(rs.moveToNext()){
                        customerID = rs.getString(0)
                        customer_email = rs.getString(1)
                        customer_pass = rs.getString(2)
                        //Toast.makeText(applicationContext,rs.getString(0),Toast.LENGTH_SHORT).show()
                    }

                    var decryptedPass = AESEncryption.decrypt(customer_pass)
                    if(customer_pass.isNotEmpty() && passGet == decryptedPass){
                        val intent = Intent(this, customerHomePage::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(applicationContext,"Email or Password is incorrect, please try again", Toast.LENGTH_SHORT).show()
                    }

                }
                catch(e: SQLiteException){
                    Toast.makeText(applicationContext,"Something went wrong, please try again", Toast.LENGTH_SHORT).show()
                }

            }







        }







    }
}