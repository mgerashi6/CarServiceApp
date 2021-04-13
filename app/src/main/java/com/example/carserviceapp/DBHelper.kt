package com.example.carserviceapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "CARSERVICEDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUSTOMER(CUSTOMER_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, FAMILY_NAME TEXT, GENDER TEXT, REGION TEXT, CITY TEXT, MOBILE_PHONE INTEGER, NATIONAL_ID INTEGER, DOB TEXT, EMAIL TEXT, PASSWORD TEXT)")
        db?.execSQL("CREATE TABLE ADMIN(ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, FAMILY_NAME TEXT, GENDER TEXT, REGION TEXT, CITY TEXT, MOBILE_PHONE INTEGER, NATIONAL_ID INTEGER, EMAIL TEXT, PASSWORD TEXT, ADMIN_TYPE TEXT)")
        db?.execSQL("CREATE TABLE CAR_INFO(CAR_PLATE_NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, CAR_REGISTRATION_NUMBER TEXT, BRAND TEXT, MODEL TEXT, YEAR INTEGER)")
        db?.execSQL("CREATE TABLE CUSTOMER_CARS(CUSTOMER_CAR_ID INTEGER PRIMARY KEY AUTOINCREMENT, CUSTOMER_ID INTEGER, CAR_PLATE_NUMBER INTEGER, CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID), CONSTRAINT FK_CAR_PLATE_NUMBER FOREIGN KEY(CAR_PLATE_NUMBER) REFERENCES CAR_INFO(CAR_PLATE_NUMBER))")
        db?.execSQL("CREATE TABLE APPOINTMENTS(APPOINTMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, CAR_PLATE_NUMBER INTEGER, CUSTOMER_ID INTEGER, DATE_TIME TEXT, SERVICE_REASON TEXT, CONSTRAINT FK_CAR_PLATE_NUMBER FOREIGN KEY(CAR_PLATE_NUMBER) REFERENCES CAR_INFO(CAR_PLATE_NUMBER), CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID))")
        db?.execSQL("CREATE TABLE MECHANIC_WORK(MECHANIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, ADMIN_ID INTEGER, CAR_PLATE_NUMBER INTEGER, CONSTRAINT FK_ADMIN_ID FOREIGN KEY(ADMIN_ID) REFERENCES ADMIN(ADMIN_ID), CONSTRAINT FK_CAR_PLATE_NUMBER FOREIGN KEY(CAR_PLATE_NUMBER) REFERENCES CAR_INFO(CAR_PLATE_NUMBER))")
        db?.execSQL("CREATE TABLE SERVICES(SERVICE_ID INTEGER PRIMARY KEY AUTOINCREMENT, SERVICE_NAME TEXT, SERVICE_PRICE INTEGER)")
        db?.execSQL("CREATE TABLE TRACK_CAR(TRACK_CAR_ID INTEGER PRIMARY KEY AUTOINCREMENT, CAR_PLATE_NUMBER INTEGER, STATUS TEXT, CURRENT_MILLAGE INTEGER, PROBLEMS TEXT, ADDITIONAL_COMMENTS TEXT, CONSTRAINT FK_CAR_PLATE_NUMBER FOREIGN KEY(CAR_PLATE_NUMBER) REFERENCES CAR_INFO(CAR_PLATE_NUMBER))")
        db?.execSQL("CREATE TABLE SERVICES_DONE(SERVICE_DONE_ID INTEGER PRIMARY KEY AUTOINCREMENT, TRACK_CAR_ID INTEGER, MECHANIC_ID INTEGER, SERVICE_ID INTEGER, CONSTRAINT FK_TRACK_CAR_ID FOREIGN KEY(TRACK_CAR_ID) REFERENCES TRACK_CAR(TRACK_CAR_ID), CONSTRAINT FK_MECHANIC_ID FOREIGN KEY(MECHANIC_ID) REFERENCES MECHANIC_WORK(MECHANIC_ID),  CONSTRAINT FK_SERVICE_ID FOREIGN KEY(SERVICE_ID) REFERENCES SERVICES(SERVICE_ID))")
        //db?.execSQL("INSERT INTO ADMIN(FIRST_NAME, FAMILY_NAME, GENDER, REGION, CITY, MOBILE_PHONE, NATIONAL_ID, EMAIL, PASSWORD, ADMIN_TYPE) VALUES ('Mohammad','Gerashi','Male','Qatar','Doha','70079955','29836400177','mgerashi@me.com','Qatar123','Customer Representative')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}