package com.example.carserviceapp

import android.content.ContentValues
import android.content.Context
import android.text.Editable

class RegisterDB {

    fun enterRegistrationDB(
        firstName: Editable,
        lastName: Editable,
        selectedGender: String,
        selectedCountry: String,
        selectedCity: String,
        mobile: Editable,
        nationalID: Editable,
        dob: Editable,
        email: Editable,
        newPass: String): ContentValues {


        var cv = ContentValues()
        cv.put("FIRST_NAME", firstName.toString())
        cv.put("FAMILY_NAME", firstName.toString())
        cv.put("GENDER", selectedGender)
        cv.put("REGION", selectedCountry )
        cv.put("CITY", selectedCity )
        cv.put("MOBILE_PHONE", mobile.toString() )
        cv.put("NATIONAL_ID", nationalID.toString() )
        cv.put("DOB", dob.toString() )
        cv.put("EMAIL", email.toString() )
        cv.put("PASSWORD", newPass )


        return cv









    }



}