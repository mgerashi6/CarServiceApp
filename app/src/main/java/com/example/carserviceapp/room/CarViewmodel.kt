package com.example.carserviceapp.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.example.carserviceapp.room.dialog.CarReportInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewmodel(application: Application) : AndroidViewModel(application) {

    var cardatabase = CarDatabase.createCarDatabase(application.applicationContext).carDao()


    var carRepository = CarRepository(cardatabase)

    var allCarInfo =  carRepository.allCarInfo

    var allappointmentInfo = carRepository.allappointmentInfo

    var allcarreportinfo = carRepository.allcarreportinfo

    fun getCarReportInfo(carplatenum : String) =  carRepository.getCarReportInfo(carplatenum)

    fun addCarInfo(carInfo: CarInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.addCarInfo(carInfo)
    }

    fun addAppointmentInfo(carAppointmentInfo: CarAppointmentInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.addCarappointmentInfo(carAppointmentInfo)
    }

    fun addReportInfo(carReportInfo: CarReportInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.addReportInfo(carReportInfo)
    }

    fun updateReportInfo(carReportInfo: CarReportInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.updateCarReportInfo(carReportInfo)
    }

    fun removeCarInfo(carInfo: CarInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.removeCarInfo(carInfo)
    }
    fun removeCarAppointmentInfo(carAppointmentInfo: CarAppointmentInfo) = viewModelScope.launch(Dispatchers.IO) {
        carRepository.removeCarAppointmentInfo(carAppointmentInfo)
    }
}