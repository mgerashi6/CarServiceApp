package com.example.carserviceapp

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carserviceapp.adapter.CarServiceDonetvAdapter
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import kotlinx.android.synthetic.main.activity_cs_car_report_check.*
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.*
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.rv_spinnerdata
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_additionalcommentsdata
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_datedat
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_problemsdata
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_statusdata
import kotlinx.android.synthetic.main.activity_mechanic_home_page.*

class cs_car_report_check : AppCompatActivity() {
    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs_car_report_check)

        homeCS.setOnClickListener {
            val intent = Intent(this, cs_home_page::class.java)
            startActivity(intent)
        }

        val intent = intent.getStringExtra("carplatenumber")

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        val adapter = CarServiceDonetvAdapter()
        rv_spinnerdata.adapter = adapter
        rv_spinnerdata.layoutManager = LinearLayoutManager(this)

        if (intent!=null){
            carViewmodel.getCarReportInfo(intent).observe(this, Observer {
                tv_statusdata.text = it.status
                tv_problemsdata.text = it.problemsdata
                tv_additionalcommentsdata.text = it.additionalComments
                adapter.spinnerlist = it.servicesdone
                tv_datedat.text = it.date
            })
        }





    }

}
