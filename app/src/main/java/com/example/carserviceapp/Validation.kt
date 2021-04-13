package com.example.carserviceapp

import android.text.Editable
import android.widget.Toast

class Validation{


     fun validation(firstName: Editable,
                    lastName: Editable,
                    mobile: Editable,
                    nationalID: Editable,
                    email: Editable): Boolean {

        var valid = true
        if(firstName.contains('\'') || firstName.contains('*') || firstName.contains('"') || firstName.isEmpty()){
            valid = false
        }
         if(lastName.contains('\'') || lastName.contains('*') || lastName.contains('"') || lastName.isEmpty()){
             valid = false
         }
         if(mobile.length>8 || mobile.isEmpty()){
             valid = false
         }
         if(nationalID.length>11 || nationalID.isEmpty()){
            valid = false
         }
         if(email.contains('\'') || email.contains('*') || email.contains('"') || email.isEmpty()){
             valid = false
         }
        return valid
    }


}