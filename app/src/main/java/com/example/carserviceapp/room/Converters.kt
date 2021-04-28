package com.example.carserviceapp.room

import androidx.room.TypeConverter
import com.example.carserviceapp.room.dialog.CustomerInfo
import com.example.carserviceapp.room.dialog.ServicesData
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromcarinfo(list: CarInfo) : String{
        return  Gson().toJson(list)
    }
    @TypeConverter
    fun tolist(list: String) : CarInfo{
        return  Gson().fromJson(list, CarInfo::class.java)
    }
    @TypeConverter
    fun fromcustomerinfo(list: CustomerInfo) : String{
        return  Gson().toJson(list)
    }
    @TypeConverter
    fun tocustomerlist(list: String) : CustomerInfo{
        return  Gson().fromJson(list, CustomerInfo::class.java)
    }
    @TypeConverter
    fun fromservicesinfo(list: List<ServicesData>) : String{
        return  Gson().toJson(list)
    }
    @TypeConverter
    fun toserviceslist(list: String) : List<ServicesData>{
        return  Gson().fromJson(list,Array<ServicesData>::class.java).toList()
    }

}