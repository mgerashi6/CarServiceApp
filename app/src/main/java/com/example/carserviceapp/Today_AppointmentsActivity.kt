package com.example.carserviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carserviceapp.adapter.CarAppointmentAdapter
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import kotlinx.android.synthetic.main.activity_today__appointments.*
import java.util.*

class Today_AppointmentsActivity : AppCompatActivity() {

    lateinit var carViewmodel: CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today__appointments)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = "$day $month, $year"

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this, carViewmodelFactory).get(CarViewmodel::class.java)

        val adapter = CarAppointmentAdapter()
        rv_todayappintms.adapter = adapter
        rv_todayappintms.layoutManager = LinearLayoutManager(this)

        carViewmodel.allappointmentInfo.observe(this, androidx.lifecycle.Observer {
            adapter.carAppointmentInfo = gettodaydate(date, it)
        })


    }

    fun gettodaydate(
        date: String,
        appointments: List<CarAppointmentInfo>
    ): List<CarAppointmentInfo> {
        var carappointmentinfo = mutableListOf<CarAppointmentInfo>()
        for (appointment in appointments) {
            if (date == appointment.date) {
                carappointmentinfo.add(appointment)
            }
        }
        return carappointmentinfo.toList()
    }
}



