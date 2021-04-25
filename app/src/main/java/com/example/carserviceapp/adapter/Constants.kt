package com.example.carserviceapp.adapter

import com.example.carserviceapp.customerID
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.dialog.CarReportInfo
import com.example.carserviceapp.room.dialog.ServicesData

object Constants {

    var services = listOf<ServicesData>(ServicesData("Oil","150"),
        ServicesData("Air Filter","100"),ServicesData("Fuel Filter","250"),
        ServicesData("Battery","550"),ServicesData("Radiator Liquid","100"),
        ServicesData("Rear Brake Pads","250"),ServicesData("Front Break Pads","250"),
        ServicesData("Engine Flush","350"))

    var status = listOf<String>("Car Received","Working On","Problem Occurred","Service Finished")

    var numofspinners = mutableListOf<Int>()

    var carReportInfo = CarReportInfo(customerID,"", listOf<ServicesData>(),"","","","")

    var update = false

    var track = false

}