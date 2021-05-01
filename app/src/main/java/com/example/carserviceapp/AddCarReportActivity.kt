package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carserviceapp.adapter.CarServiceDoneAdapter
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.adapter.Constants.numofspinners
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.CarReportInfo
import com.example.carserviceapp.room.dialog.ServicesData
import kotlinx.android.synthetic.main.activity_add_car_report.*
import kotlinx.android.synthetic.main.activity_add_car_report.cd_save
import kotlinx.android.synthetic.main.activity_add_car_report.ed_additionalcomments
import kotlinx.android.synthetic.main.activity_add_car_report.ed_carplatenum
import kotlinx.android.synthetic.main.activity_add_car_report.ed_problems
import kotlinx.android.synthetic.main.activity_customer_car_report.*
import java.util.*

class AddCarReportActivity : AppCompatActivity() {

    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car_report)

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = "$day $month, $year"

        putdatainStatusSpinner()


        val adapter2 = CarServiceDoneAdapter()
        rv_spinners.adapter = adapter2
        rv_spinners.layoutManager = LinearLayoutManager(this)


        if (Constants.update){
            val carreportinfo = Constants.carReportInfo
            ed_carplatenum.setText(carreportinfo.carplatenum)
            ed_problems.setText(carreportinfo.problemsdata)
            ed_additionalcomments.setText(carreportinfo.additionalComments)
            Constants.numofspinners = getPosofspinner(carreportinfo.servicesdone)
            adapter2.spinnerlist = numofspinners
            cd_update.visibility = View.VISIBLE
            cd_save.visibility = View.GONE
        }else{
            cd_update.visibility = View.GONE
            cd_save.visibility = View.VISIBLE
        }


        fab_addnewspinner.setOnClickListener {
            val numofitems = Constants.numofspinners.size + 1
            numofspinners.add(numofitems)
            adapter2.spinnerlist = numofspinners
            adapter2.notifyDataSetChanged()
        }

        var carReportlist = listOf<CarReportInfo>()



        cd_update.setOnClickListener {
            val status = status_data2.selectedItem.toString()
            val servicedonelist = servicedonelist()
            val carplatenum = ed_carplatenum.text.toString()
            val problemdata = ed_problems.text.toString()
            val additionalcomments = ed_additionalcomments.text.toString()
            if (status.isNotEmpty() && servicedonelist.isNotEmpty() && carplatenum.isNotEmpty()
                && problemdata.isNotEmpty() && additionalcomments.isNotEmpty()){
                val carReportInfo = CarReportInfo(customerID, status,if (servicedonelist.isEmpty()) listOf<ServicesData>() else servicedonelist,if (problemdata.isEmpty()) "" else problemdata,
                    carplatenum,date,if (additionalcomments.isEmpty()) "" else additionalcomments, Constants.carReportInfo.id)
                carViewmodel.updateReportInfo(carReportInfo)
                Toast.makeText(this,"Car Report Updated Succesfully",Toast.LENGTH_SHORT).show()
            }

        }

        cd_home.setOnClickListener {
            startActivity(Intent(Intent(this,MechanicHomePage::class.java)))
        }

        cd_save.setOnClickListener {
            val status = status_data2.selectedItem.toString()
            val servicedonelist = servicedonelist()
            val carplatenum = ed_carplatenum.text.toString()
            val problemdata = ed_problems.text.toString()
            val additionalcomments = ed_additionalcomments.text.toString()
            if (status.isNotEmpty()  && carplatenum.isNotEmpty() ){
                if (checkCarPlatenumber(carReportlist,carplatenum)){
                    val carReportInfo = CarReportInfo(customerID, status,if (servicedonelist.isEmpty()) listOf<ServicesData>() else servicedonelist,if (problemdata.isEmpty()) "" else problemdata,
                        carplatenum,date,if (additionalcomments.isEmpty()) "" else additionalcomments)
                    carViewmodel.addReportInfo(carReportInfo)
                    Toast.makeText(this,"Car Report Added Succesfully",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Try different Car Platenumber",Toast.LENGTH_SHORT).show()
                }
            }


        }
        carViewmodel.allcarreportinfo.observe(this, androidx.lifecycle.Observer {
            carReportlist = it
        })


    }

    private fun putdatainStatusSpinner() {
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,
            Constants.status
        )

        //    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        status_data2?.adapter = adapter
        if (Constants.carReportInfo.status != ""){
            status_data2.setSelection(Constants.status.indexOf(Constants.carReportInfo.status))
        }

        status_data2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(status_data2?.selectedItemPosition ==0){
                    condition1()
                }
            }
        }
    }


    private fun condition1(){
        Toast.makeText(this,"Please select a correct option", Toast.LENGTH_SHORT).show()
    }

    fun servicedonelist() : List<ServicesData>{
        var serviceslist = mutableListOf<ServicesData>()
        for (spinner in numofspinners){
            serviceslist.add(addServicedata(spinner))
        }
        return serviceslist.toList()
    }

    override fun onDestroy() {
        Constants.numofspinners = mutableListOf(0)
        super.onDestroy()
    }
    fun addServicedata(pos : Int) : ServicesData{
        return Constants.services[pos]
    }

    fun getPosofspinner(carreportlist : List<ServicesData>) : MutableList<Int>{
        var postions = mutableListOf<Int>()
        for (carreport in carreportlist){
            postions.add(Constants.services.indexOf(carreport))
        }
        return postions
    }
    fun checkCarPlatenumber(carReportInfo: List<CarReportInfo>,carPlatenum : String) : Boolean {
        for (carReport in carReportInfo){
            if (carReport.carplatenum == carPlatenum){
                return false
            }
        }
        return true
    }

}