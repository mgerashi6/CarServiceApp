package com.example.carserviceapp.room.dialog

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.carserviceapp.room.CarInfo


@Entity(tableName = "car_appointmentinfo")
data class CarAppointmentInfo(
        val customerID : String,
    val platenumber : String,
    val date : String,
    val time : String,
    val reason : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) {
}