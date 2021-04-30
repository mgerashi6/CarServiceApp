package com.example.carserviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carserviceapp.adapter.CarServiceDonetvAdapter
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.CarReportInfo
import com.example.carserviceapp.room.dialog.ServicesData
import kotlinx.android.synthetic.main.activity_add_car_report.*
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.*
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_additionalcomments
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_problems
import kotlinx.android.synthetic.main.activity_customer_car_report_selected.tv_status

class customerCarReportSelected : AppCompatActivity() {
    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_car_report_selected)

        var dateServiced = ""
        var status = ""
        var problems = ""
        var additionalComments = ""

        val intent = intent.getStringExtra("carplatenum")

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        if (Constants.track){
            payment.visibility = View.INVISIBLE
            digital_receipt.visibility = View.INVISIBLE
        }else{
            payment.visibility = View.VISIBLE
            digital_receipt.visibility = View.VISIBLE
        }

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

     /*   var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT DATE FROM MECHANIC_WORK WHERE CAR_INFO_ID = '$carInfoID'  )", null)
        if(rs.moveToNext()){
            dateServiced = rs.getString(0)
        }

        var rss = db.rawQuery("SELECT STATUS, PROBLEMS, ADDITIONAL_COMMENTS FROM TRACK_CAR WHERE CAR_INFO_ID = '$carInfoID'  )", null)
        if(rss.moveToNext()){
            status = rs.getString(0)
            problems  = rs.getString(1)
            additionalComments = rs.getString(2)
        }*/




    }

    }
