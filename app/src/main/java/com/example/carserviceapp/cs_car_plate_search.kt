package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import kotlinx.android.synthetic.main.activity_cs_car_plate_search.*
import kotlinx.android.synthetic.main.activity_customer_car_report.*
import kotlinx.android.synthetic.main.activity_customer_car_report.check_report
import kotlinx.android.synthetic.main.activity_customer_car_report.plate_number

class cs_car_plate_search : AppCompatActivity() {
    lateinit var carViewmodel : CarViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_car_plate_search)

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)
        carViewmodel.allCarInfo.observe(this, Observer {

        })
        CPSbutton.setOnClickListener {

            val intent = Intent(this, cs_car_report_check::class.java)
            intent.putExtra("carplatenumber",plate_number.text.toString())
            startActivity(intent)
        }



    }


//    fun getPlatenumber(carinfolist : List<CarInfo>) : List<String>{
//        var platenumList = mutableListOf<String>()
//        for (carinfo in carinfolist){
//            platenumList.add(carinfo.carRegistrationNumber)
//        }
//        return platenumList.toList()
//    }
}