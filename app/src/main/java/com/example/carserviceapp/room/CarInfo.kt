package com.example.carserviceapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "car_info")
data class CarInfo(
        val customerID : String,
    val carPlateNumber : String,
    val carRegistrationNumber : String,
    val brand : String,
    val model : String,
    val year : Int,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) {
}