package com.example.carserviceapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.example.carserviceapp.room.dialog.CarReportInfo
import com.example.carserviceapp.room.dialog.CustomerInfo


@Database(entities = [CarInfo::class,CarAppointmentInfo::class,CustomerInfo::class,CarReportInfo::class],version = 2,exportSchema = false)
@TypeConverters(Converters::class)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao() : CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarDatabase? = null
        fun createCarDatabase(context: Context): CarDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "carDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
