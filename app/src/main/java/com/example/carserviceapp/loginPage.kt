package com.example.carserviceapp

import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.carserviceapp.fragments.CustomerCallFragment
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration_part_one.*

var customerID = ""
class loginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)


        admin.setOnClickListener {
            val intent = Intent(this, admin_login::class.java)
            startActivity(intent)
        }


        signin_button.setOnClickListener {
            var emailInput = findViewById<EditText>(R.id.email_input).text.toString()
            var passGet = findViewById<EditText>(R.id.pass_input).text.toString()



            if(emailInput.isEmpty() || passGet.isEmpty()){
                Toast.makeText(applicationContext,"Please enter email and password", Toast.LENGTH_SHORT).show()

            }
            else{
                try{
                    var customerEmail = ""
                    var customerPass = ""

                    var helper = DBHelper(applicationContext)
                    var db = helper.readableDatabase
                    var rs = db.rawQuery("SELECT CUSTOMER_ID, EMAIL, PASSWORD FROM CUSTOMER WHERE EMAIL = '$emailInput'", null)
                    //Log.d("check", rs.getString(0))
                    if(rs.moveToNext()){
                        customerID = rs.getString(0)
                        customerEmail = rs.getString(1)
                        customerPass = rs.getString(2)
                        //Toast.makeText(applicationContext,rs.getString(0),Toast.LENGTH_SHORT).show()
                    }


                    var decryptedPass = AESEncryption.decrypt(customerPass)
                    if(customerPass.isNotEmpty() && passGet == decryptedPass){
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