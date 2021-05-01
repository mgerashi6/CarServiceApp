package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.adapter.CarInfoAdapter
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.AddCarInfoDialog
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_customer_all_vehicles.*
import kotlinx.android.synthetic.main.activity_customer_appointments_booked.*

class customerAllVehicles : AppCompatActivity() {

    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_all_vehicles)

        homeVeh.setOnClickListener{
            val intent = Intent(this, customerHomePage::class.java)
            startActivity(intent)
        }
        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        val adapter = CarInfoAdapter()
        vp_carinfo.adapter = adapter

        TabLayoutMediator(tab_layout,vp_carinfo){tab,position ->
            tab.text = "Car ${position+1}"
        }.attach()

        fab_add.setOnClickListener {
            val dialog = AddCarInfoDialog(this,carViewmodel,adapter)
            dialog.show()
        }
        adapter.setOnItemClickListener {
            carViewmodel.removeCarInfo(it)
            Toast.makeText(this,"Removed Successfully",Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }

        carViewmodel.allCarInfo.observe(this, Observer {

            adapter.carinfolist = filterbycustomerID(customerID,it)
        })

    }
    fun filterbycustomerID(customerID : String,carInfolist : List<CarInfo>) : List<CarInfo>{
        var carlist = mutableListOf<CarInfo>()
        for (car in carInfolist){
            if (car.customerID == customerID){
                carlist.add(car)
            }
        }
        return carlist
    }
}