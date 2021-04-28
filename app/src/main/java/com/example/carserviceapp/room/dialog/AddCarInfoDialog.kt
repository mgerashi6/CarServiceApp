package com.example.carserviceapp.room.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.example.carserviceapp.R
import com.example.carserviceapp.adapter.CarInfoAdapter
import com.example.carserviceapp.customerID
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.CarViewmodel
import kotlinx.android.synthetic.main.activity_add_car_report.*
import kotlinx.android.synthetic.main.caradd_dialog.*

class AddCarInfoDialog(context: Context,val carViewmodel: CarViewmodel,val carInfoAdapter: CarInfoAdapter) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.caradd_dialog)


        cd_add.setOnClickListener {
            var registrationnum = ed_registernum.text.toString()
            var brand = ed_branddata.text.toString()
            var model = ed_modeldata.text.toString()
            var year = ed_yeardata.text.toString()
            var carplatenum = ed_cpnumdata.text.toString()
            if (registrationnum.isNotEmpty() && brand.isNotEmpty() &&  model.isNotEmpty() &&
                year.isNotEmpty() ){
                val carInfo = CarInfo(customerID, carplatenum,registrationnum,brand,model,year.toInt())
                carViewmodel.addCarInfo(carInfo)
                carInfoAdapter.notifyDataSetChanged()
                dismiss()
            }else{
                Toast.makeText(context,"Please fill all details",Toast.LENGTH_SHORT).show()
            }

        }
    }
}