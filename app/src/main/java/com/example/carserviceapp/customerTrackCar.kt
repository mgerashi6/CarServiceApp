package com.example.carserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.adapter.Constants
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import kotlinx.android.synthetic.main.activity_customer_car_report.*
import kotlinx.android.synthetic.main.activity_customer_car_report.plate_number
import kotlinx.android.synthetic.main.activity_customer_track_car.*

class customerTrackCar : AppCompatActivity() {

    lateinit var carViewmodel : CarViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_track_car)



        var carViewmodelFactory = CarViewmodelFactory(application)
        carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)


        carViewmodel.allCarInfo.observe(this, Observer {
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, getPlatenumber(it))
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            plate_number2.adapter = adapter
            plate_number2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    if(plate_number2.selectedItemPosition ==0){
                        condition1()
                    }
                    carPlate = plate_number2.selectedItem.toString()

                }
            }
        })

        track.setOnClickListener {
            //      var rss = db.rawQuery("SELECT CAR_INFO_ID FROM CAR_INFO WHERE CAR_PLATE_NUMBER = '$carPlate'  )", null)

            //     while(rss.moveToNext()){
            //       carInfoID =  rss.getString(0)

            // }
            val intent = Intent(this, customerCarReportSelected::class.java)
            intent.putExtra("carplatenum", carPlate)
            startActivity(intent)
            Constants.track = true
        }

        homeTrack.setOnClickListener{
            val intent = Intent(this, customerHomePage::class.java)
            startActivity(intent)
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