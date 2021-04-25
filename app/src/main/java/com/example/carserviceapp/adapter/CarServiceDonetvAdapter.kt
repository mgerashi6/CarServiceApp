package com.example.carserviceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carserviceapp.R
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import com.example.carserviceapp.room.dialog.ServicesData
import kotlinx.android.synthetic.main.activity_add_car_report.*
import kotlinx.android.synthetic.main.activity_customer_all_vehicles.view.*
import kotlinx.android.synthetic.main.rv_spinner_report.view.*
import kotlinx.android.synthetic.main.rv_spinner_reportselected.view.*
import kotlinx.android.synthetic.main.vp_appointment.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.cd_remove

class CarServiceDonetvAdapter(
) : RecyclerView.Adapter<CarServiceDonetvAdapter.PlaylistViewtvHolder>() {
    inner class PlaylistViewtvHolder(itemview : View) : RecyclerView.ViewHolder(itemview)

    private val diffCallback = object : DiffUtil.ItemCallback<ServicesData>(){

        override fun areItemsTheSame(oldItem: ServicesData, newItem: ServicesData): Boolean {
            return oldItem.serviceName   == newItem.servicePrice
        }

        override fun areContentsTheSame(oldItem: ServicesData, newItem: ServicesData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var spinnerlist : List<ServicesData>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarServiceDonetvAdapter.PlaylistViewtvHolder {
        return PlaylistViewtvHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_spinner_reportselected,parent,false))
    }

    override fun getItemCount(): Int {
        return spinnerlist.size
    }

    override fun onBindViewHolder(holder: PlaylistViewtvHolder, position: Int) {
        val data = spinnerlist[position]
        holder.itemView.apply {
            tv_service.text = data.serviceName
            tv_serviceprice.text = data.servicePrice
        }
    }
}

