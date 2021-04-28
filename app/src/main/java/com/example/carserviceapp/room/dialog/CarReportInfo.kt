package com.example.carserviceapp.room.dialog

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "carreport_info")
data class CarReportInfo(
        val customerID : String,
  val status : String,
  val servicesdone : List<ServicesData>,
  val problemsdata : String,
  val carplatenum : String,
  val date : String,
  val additionalComments : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) {
}