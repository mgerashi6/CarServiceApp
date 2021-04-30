package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import kotlinx.android.synthetic.main.activity_customer_car_report.*
import kotlinx.android.synthetic.main.activity_customer_car_report.plate_number
import kotlinx.android.synthetic.main.activity_customer_track_car.*
import kotlinx.android.synthetic.main.activity_registration_part_one.*


var dropDownListCarPlate = arrayOf("Select Car Plate Number")
var carPlate = ""
var carInfoID = ""
class customerCarReport : AppCompatActivity() {

    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_car_report)

        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

     /*   Log.d("CusID", customerID)
        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT CAR_INFO_ID, CAR_PLATE_NUMBER FROM CAR_INFO WHERE CAR_INFO_ID IN (SELECT CUSTOMER_CARS.CAR_INFO_ID FROM CUSTOMER_CARS WHERE CUSTOMER_ID = '$customerID' )", null)


        while(rs.moveToNext()){
            dropDownListCarPlate = append(dropDownListCarPlate, rs.getString(1))
            //Toast.makeText(applicationContext,rs.getString(1),Toast.LENGTH_LONG).show()
        } */
        carViewmodel.allCarInfo.observe(this, Observer {
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, getPlatenumber(it))
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            plate_number.adapter = adapter
            plate_number.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    if(plate_number.selectedItemPosition ==0){
                        condition1()
                    }
                    carPlate = plate_number.selectedItem.toString()

                }
            }
        })





        check_report.setOnClickListener {
      //      var rss = db.rawQuery("SELECT CAR_INFO_ID FROM CAR_INFO WHERE CAR_PLATE_NUMBER = '$carPlate'  )", null)

       //     while(rss.moveToNext()){
         //       carInfoID =  rss.getString(0)

           // }
            val intent = Intent(this, customerCarReportSelected::class.java)
            intent.putExtra("carplatenum", carPlate)
            startActivity(intent)
            Constants.track = false
        }

    }

    private fun condition1(){
        Toast.makeText(this,"Please select a correct option", Toast.LENGTH_SHORT).show()
    }


    private fun append(arr: Array<String>, element: String): Array<String> {
        val list: MutableList<String> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun getPlatenumber(carinfolist : List<CarInfo>) : List<String>{
        var platenumList = mutableListOf<String>()
        for (carinfo in carinfolist){
            platenumList.add(carinfo.carPlateNumber)
        }
        return platenumList.toList()
    }

}