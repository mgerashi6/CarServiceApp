package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carserviceapp.fragments.CustomerCallFragment
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.fragments.CustomerProfileFragment
import kotlinx.android.synthetic.main.activity_customer_home_page.*
import kotlinx.android.synthetic.main.activity_customer_home_page.bottom_nav
import kotlinx.android.synthetic.main.fragment_customer_home.*

class customerHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home_page)

        val CustomerhomeFragment = CustomerHomeFragment()
        //val CustomerProfileFragment = CustomerProfileFragment()
        val CustomerCallFragment = CustomerCallFragment()

        makeCurrentFragment(CustomerhomeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(CustomerhomeFragment)
                //R.id.ic_account -> makeCurrentFragment(CustomerProfileFragment)
                R.id.ic_call -> makeCurrentFragment(CustomerCallFragment)
            }
            true
        }
    }

        private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


}