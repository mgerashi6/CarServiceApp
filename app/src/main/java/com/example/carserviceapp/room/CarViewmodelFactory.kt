package com.example.carserviceapp.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CarViewmodelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CarViewmodel::class.java)){
            return CarViewmodel(application) as T
        }
        throw IllegalArgumentException("Viewmodel class not found")
    }
}