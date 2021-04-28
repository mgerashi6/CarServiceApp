package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carserviceapp.adapter.CarServiceUpdateAdapter
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.adapter.Constants.carReportInfo
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.CarReportInfo
import com.example.carserviceapp.room.dialog.ServicesData
import kotlinx.android.synthetic.main.activity_update_car_report.*

class UpdateCarReportActivity : AppCompatActivity() {

    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_car_report)

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)


        val adapter = CarServiceUpdateAdapter()
        rv_carplatenums.adapter = adapter
        rv_carplatenums.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener {
            Constants.carReportInfo = it
            Constants.update = true
            val intent = Intent(this, AddCarReportActivity::class.java)
            startActivity(intent)
        }

        add_car_reports1.setOnClickListener {
            Constants.update = false
            carReportInfo = CarReportInfo(customerID,"", listOf<ServicesData>(),"","","","")
            val intent = Intent(this, AddCarReportActivity::class.java)
            startActivity(intent)
        }

        carViewmodel.allcarreportinfo.observe(this, Observer {
            adapter.spinnerlist = filterbycustomerID(customerID,it)
        })


    }
    fun filterbycustomerID(customerID : String,carReportInfolist : List<CarReportInfo>) : List<CarReportInfo>{
        var carReportlist = mutableListOf<CarReportInfo>()
        for (car in carReportInfolist){
            if (car.customerID == customerID){
                carReportlist.add(car)
            }
        }
        return carReportlist
    }
}