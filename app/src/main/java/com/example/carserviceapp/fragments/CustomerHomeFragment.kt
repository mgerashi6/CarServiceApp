package com.example.carserviceapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carserviceapp.*
import kotlinx.android.synthetic.main.fragment_customer_home.*

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerHomeFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        book_appointment.setOnClickListener {
            val intent = Intent(activity, customerBookAppointment::class.java)
            startActivity(intent)
        }


        appointments_booked.setOnClickListener {
            val intent = Intent(activity, customerAppointmentsBooked::class.java)
            startActivity(intent)
        }

        track_car.setOnClickListener {
            val intent = Intent(activity, customerTrackCar::class.java)
            startActivity(intent)
        }

        check_car_reports.setOnClickListener {
            val intent = Intent(activity, customerCarReport::class.java)
            startActivity(intent)
        }


        all_vehicles.setOnClickListener {
            val intent = Intent(activity, customerAllVehicles::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val v = inflater.inflate(R.layout.fragment_customer_home, container, false)

        return v

    }






//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment CustomerHomeFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            CustomerHomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}