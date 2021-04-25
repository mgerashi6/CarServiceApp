package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carserviceapp.fragments.CustomerCallFragment
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.fragments.CustomerProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_customer_home_page.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


// How to encrypt and decrypt //
//        var pass = "Qatar123333333"
//        var newnc = AESEncryption.encrypt(pass)
//        var newncc = AESEncryption.decrypt(newnc.toString())
//
//        Log.d("encrypt", newnc.toString())
//        Log.d("decrypt", newncc.toString() )

        // -----------------------------------------
//        val CustomerhomeFragment = CustomerHomeFragment()
//        val CustomerProfileFragment = CustomerProfileFragment()
//        val CustomerCallFragment = CustomerCallFragment()
//
//        makeCurrentFragment(CustomerhomeFragment)
//
//        bottom_nav.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.ic_home -> makeCurrentFragment(CustomerhomeFragment)
//                R.id.ic_account -> makeCurrentFragment(CustomerProfileFragment)
//                R.id.ic_call -> makeCurrentFragment(CustomerCallFragment)
//            }
//            true
//        }

        //Database Stuff
        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM ADMIN",null)

        if(rs.moveToNext()){
            Toast.makeText(applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(applicationContext,"",Toast.LENGTH_LONG).show()
        }





        existing_customer.setOnClickListener {
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }

        new_customer.setOnClickListener{
            val intent = Intent(this, RegistrationPartOne::class.java)
            startActivity(intent)
        }

    }

//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply{
//            replace(R.id.fl_wrapper, fragment)
//            commit()
//        }

}





