package com.example.carserviceapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.example.carserviceapp.room.dialog.CarReportInfo

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCarInfo(carInfo : CarInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCarReportInfo(carReportInfo: CarReportInfo)

    @Update
    suspend fun updateCarReportInfo(carReportInfo: CarReportInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addappointmentInfo(carAppointmentInfo: CarAppointmentInfo)

    @Delete
    suspend fun removeCarInfo(carInfo: CarInfo)

    @Delete
    suspend fun removeCarAppointmentInfo(carAppointmentInfo: CarAppointmentInfo)

    @Query("SELECT * FROM car_info ORDER BY id ASC")
    fun getAllCarInfo() : LiveData<List<CarInfo>>

    @Query("SELECT * FROM carreport_info ORDER BY id ASC")
    fun allCarReportInfo() : LiveData<List<CarReportInfo>>

    @Query("SELECT * FROM carreport_info WHERE carplatenum == :carplatenum")
    fun getAllCarReportInfo(carplatenum : String) : LiveData<CarReportInfo>

    @Query("SELECT * FROM car_appointmentinfo ORDER BY id ASC")
    fun getAllAppointmentInfo() : LiveData<List<CarAppointmentInfo>>

}