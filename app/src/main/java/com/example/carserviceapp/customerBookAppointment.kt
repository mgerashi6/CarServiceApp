package com.example.carserviceapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.carserviceapp.fragments.CustomerHomeFragment
import com.example.carserviceapp.room.CarViewmodel
import com.example.carserviceapp.room.CarViewmodelFactory
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import kotlinx.android.synthetic.main.activity_customer_book_appointment.*
import kotlinx.android.synthetic.main.activity_customer_home_page.*
import kotlinx.android.synthetic.main.activity_registration_part_one.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class customerBookAppointment : AppCompatActivity() {

  //  var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
   // var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
  lateinit var carViewmodel : CarViewmodel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_book_appointment)

        var carViewmodelFactory = CarViewmodelFactory(application)
      carViewmodel = ViewModelProvider(this,carViewmodelFactory).get(CarViewmodel::class.java)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            dateBTN.setText("" + dayOfMonth + " " + month + ", " + year)

        }, year, month, day)

        dateBTN.setOnClickListener {
            dpd.show()
        }

      var am_pm = ""

      val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
          c.set(Calendar.HOUR_OF_DAY, hour)
          c.set(Calendar.MINUTE, minute)
          timeBTN.text = SimpleDateFormat("HH:mm a").format(c.time)
      }
      timeBTN.setOnClickListener {
          TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
      }

      book_appointment.setOnClickListener {
          val date = dateBTN.text.toString()
          val time = timeBTN.text.toString()
          val platenumber = car_plate_number.text.toString()
          val reason = reason_for_service.text.toString()
          if (date.isNotEmpty() && time.isNotEmpty() && platenumber.isNotEmpty() && reason.isNotEmpty()){
              val appointmentInfo = CarAppointmentInfo(customerID,platenumber,date,time,reason)
              carViewmodel.addAppointmentInfo(appointmentInfo)
              val intent = Intent(this,customerAppointmentsBooked::class.java)
              startActivity(intent)
              Toast.makeText(this,"Successfully Appointment booked",Toast.LENGTH_SHORT).show()

          }else{
              Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show()
          }
      }


        homeApp.setOnClickListener{
            val intent = Intent(this,customerHomePage::class.java)
            startActivity(intent)
        }







    }
}


      /*  var dateChosen = ""
        var timeChosen = ""

        dateBTN.setOnClickListener {
            val nowMonth = Calendar.getInstance()
            try{
                if(dateBTN.text != "Date") {
                    val datee = formate.parse(dateBTN.text.toString())
                    nowMonth.time = datee
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateBTN.text = formate.format(selectedDate.time)
                val date = formate.format(selectedDate.time)
                dateChosen = date

            },
                    nowMonth.get(Calendar.YEAR), nowMonth.get(Calendar.MONTH), nowMonth.get(Calendar.DAY_OF_MONTH))
            datePicker.show()

        }


        timeBTN.setOnClickListener {
            val nowTime = Calendar.getInstance()
            try{
                if(timeBTN.text != "Time") {
                    val timee = timeFormat.parse(timeBTN.text.toString())
                    nowTime.time = timee
                }
                }
                catch (e: Exception){
                    e.printStackTrace()
                }

            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                timeBTN.text = timeFormat.format(selectedTime.time)
                timeChosen = timeFormat.format(selectedTime.time)
            },
                    nowTime.get(Calendar.HOUR_OF_DAY), nowTime.get(Calendar.MINUTE), false)
            timePicker.show()

        }


        book_appointment.setOnClickListener {
            Toast.makeText(this, dateChosen, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, timeChosen, Toast.LENGTH_SHORT).show()

        }*/




