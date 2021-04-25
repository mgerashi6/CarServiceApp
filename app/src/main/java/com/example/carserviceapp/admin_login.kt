package com.example.carserviceapp

import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_page.*

var adminID = ""
class admin_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        signin_button.setOnClickListener {
            var emailInput = findViewById<EditText>(R.id.email_input).text.toString()
            var passGet = findViewById<EditText>(R.id.pass_input).text.toString()



            if(emailInput.isEmpty() || passGet.isEmpty()){
                Toast.makeText(applicationContext,"Please enter email and password", Toast.LENGTH_SHORT).show()

            }
            else{
                try{
                    var adminEmail = ""
                    var adminPass = ""
                    var adminType = ""

                    var helper = DBHelper(applicationContext)
                    var db = helper.readableDatabase
                    var rs = db.rawQuery("SELECT ADMIN_ID, EMAIL, PASSWORD, ADMIN_TYPE FROM CUSTOMER WHERE EMAIL = '$emailInput'", null)
                    //Log.d("check", rs.getString(0))
                    if(rs.moveToNext()){
                        adminID = rs.getString(0)
                        adminEmail = rs.getString(1)
                        adminPass = rs.getString(2)
                        adminType = rs.getString(3)
                        //Toast.makeText(applicationContext,rs.getString(0),Toast.LENGTH_SHORT).show()
                    }


                    var decryptedPass = AESEncryption.decrypt(adminPass)
                    if(adminPass.isNotEmpty() && passGet == decryptedPass){
//                        if(adminType == "Mechanic"){
//                            val intent = Intent(this, ENTERMECHANICPAGENAME::class.java)
//                            startActivity(intent)
//                        }
                         if(adminType == "CSRep"){
                            val intent = Intent(this, cs_home_page::class.java)
                            startActivity(intent)
                        }

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