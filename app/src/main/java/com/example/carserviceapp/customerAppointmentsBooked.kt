package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.adapter.CarAppointmentAdapter
import com.example.carserviceapp.adapter.CarInfoAdapter
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.AddCarInfoDialog
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_customer_all_vehicles.*
import kotlinx.android.synthetic.main.activity_customer_appointments_booked.*

class customerAppointmentsBooked : AppCompatActivity() {
    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_appointments_booked)

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        val adapter = CarAppointmentAdapter()
        vp_carappointmentinfo.adapter = adapter

        TabLayoutMediator(tab_layout_appointment,vp_carappointmentinfo){tab,position ->
            tab.text = "Appointment${position+1}"
        }.attach()

        adapter.setOnItemClickListener {
            carViewmodel.removeCarAppointmentInfo(it)
            Toast.makeText(this,"Removed Successfully", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }

        carViewmodel.allappointmentInfo.observe(this, Observer {
            adapter.carAppointmentInfo = filterbycustomerID(customerID,it)
        })

        homeBook.setOnClickListener{
            val intent = Intent(this, customerHomePage::class.java)
            startActivity(intent)
        }

    }
    fun filterbycustomerID(customerID : String,appointmentInfolist : List<CarAppointmentInfo>) : List<CarAppointmentInfo>{
        var appointmentlist = mutableListOf<CarAppointmentInfo>()
        for (car in appointmentInfolist){
            if (car.customerID == customerID){
                appointmentlist.add(car)
            }
        }
        return appointmentlist
    }



}