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
import kotlinx.android.synthetic.main.vp_appointment.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.cd_remove

class CarServiceDoneAdapter(
) : RecyclerView.Adapter<CarServiceDoneAdapter.PlaylistViewHolder>() {
    inner class PlaylistViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)

    private val diffCallback = object : DiffUtil.ItemCallback<Int>(){

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem   == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var spinnerlist : List<Int>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_spinner_report,parent,false))
    }

    override fun getItemCount(): Int {
        return spinnerlist.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val data = spinnerlist[position]
        holder.itemView.apply {


            val adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item,
                getservicename(Constants.services)
            )
            //    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            service_one11?.adapter = adapter
            if (data < Constants.services.size){
                service_one11?.setSelection(data)
            }else{
                service_one11?.setSelection(data)
            }

            service_one11?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    if(service_one11?.selectedItemPosition ==0) {
                    }

                    Constants.numofspinners[position] = service_one11?.selectedItemPosition!!

                    when(service_one11?.selectedItemPosition){
                        1 -> changeTextinEd(ed_serviceone,Constants.services[0].servicePrice)
                        2 -> changeTextinEd(ed_serviceone,Constants.services[1].servicePrice)
                        3 -> changeTextinEd(ed_serviceone,Constants.services[2].servicePrice)
                        4 -> changeTextinEd(ed_serviceone,Constants.services[3].servicePrice)
                        5 -> changeTextinEd(ed_serviceone,Constants.services[4].servicePrice)
                        6 -> changeTextinEd(ed_serviceone,Constants.services[5].servicePrice)
                        7 -> changeTextinEd(ed_serviceone,Constants.services[6].servicePrice)
                        8 -> changeTextinEd(ed_serviceone,Constants.services[7].servicePrice)
                    }
                }
            }
            }
        }
    }
    fun getservicename(services : List<ServicesData>) : List<String>{
        var servicenamelist = mutableListOf<String>("Choose")
        for (service in services){
            servicenamelist.add(service.serviceName)
        }
        return servicenamelist.toList()
    }
private fun changeTextinEd(ed: EditText?, price : String) {
    ed?.setText("")
    ed?.setText(price)
}

