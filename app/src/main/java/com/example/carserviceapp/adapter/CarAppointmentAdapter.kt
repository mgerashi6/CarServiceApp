package com.example.carserviceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carserviceapp.R
import com.example.carserviceapp.room.CarInfo
import com.example.carserviceapp.room.dialog.CarAppointmentInfo
import kotlinx.android.synthetic.main.activity_customer_all_vehicles.view.*
import kotlinx.android.synthetic.main.vp_appointment.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.cd_remove

class CarAppointmentAdapter(
) : RecyclerView.Adapter<CarAppointmentAdapter.PlaylistViewHolder>() {
    inner class PlaylistViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)

    private val diffCallback = object : DiffUtil.ItemCallback<CarAppointmentInfo>(){
        override fun areItemsTheSame(
            oldItem: CarAppointmentInfo,
            newItem: CarAppointmentInfo
        ): Boolean {
            return oldItem.id   == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CarAppointmentInfo,
            newItem: CarAppointmentInfo
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var carAppointmentInfo : List<CarAppointmentInfo>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((CarAppointmentInfo) -> Unit)? = null

    fun setOnItemClickListener(listener: (CarAppointmentInfo) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vp_appointment,parent,false))
    }

    override fun getItemCount(): Int {
        return carAppointmentInfo.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val data = carAppointmentInfo[position]
        holder.itemView.apply {
            tv_appiontmentdata.text = data.customerID.toString()
            tv_datedata.text = data.date
            tv_timedata.text = data.time
            tv_platenumdata.text = data.platenumber
            tv_reasondata.text = data.reason

            cd_remove.setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }
        }
    }

}