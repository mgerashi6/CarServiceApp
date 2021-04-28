package com.example.carserviceapp.room

import androidx.lifecycle.LiveData
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.example.carserviceapp.room.dialog.CarReportInfo

class CarRepository(val carDao : CarDao) {

    var allCarInfo : LiveData<List<CarInfo>> = carDao.getAllCarInfo()

    var allappointmentInfo : LiveData<List<CarAppointmentInfo>> = carDao.getAllAppointmentInfo()

    var allcarreportinfo : LiveData<List<CarReportInfo>> = carDao.allCarReportInfo()


    fun getCarReportInfo(carplateNum : String) = carDao.getAllCarReportInfo(carplateNum)



    suspend fun addCarInfo(carInfo: CarInfo){
        carDao.addCarInfo(carInfo)
    }

    suspend fun updateCarReportInfo(carReportInfo: CarReportInfo){
        carDao.updateCarReportInfo(carReportInfo)
    }


    suspend fun addCarappointmentInfo(carAppointmentInfo: CarAppointmentInfo){
        carDao.addappointmentInfo(carAppointmentInfo)
    }


    suspend fun addReportInfo(carReportInfo: CarReportInfo){
        carDao.addCarReportInfo(carReportInfo)
    }

    suspend fun removeCarInfo(carInfo: CarInfo){
        carDao.removeCarInfo(carInfo)
    }

    suspend fun removeCarAppointmentInfo(carAppointmentInfo: CarAppointmentInfo){
        carDao.removeCarAppointmentInfo(carAppointmentInfo)
    }
}