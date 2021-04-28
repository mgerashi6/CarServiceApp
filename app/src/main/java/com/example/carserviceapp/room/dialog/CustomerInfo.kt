package com.example.carserviceapp.room.dialog

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customer_info")
data class CustomerInfo(
    val firstName : String,
    val familyName : String,
    val gender : String,
    val region : String,
    val city : String,
    val mobilenum : String,
    val nationalID : String,
    val dob : String,
    val email : String,
    val password : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) {
}