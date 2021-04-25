package com.example.carserviceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.carserviceapp.R
import com.example.carserviceapp.room.CarInfo
import kotlinx.android.synthetic.main.activity_customer_all_vehicles.view.*
import kotlinx.android.synthetic.main.vp_carinfo.view.*

class CarInfoAdapter(
) : RecyclerView.Adapter<CarInfoAdapter.PlaylistViewHolder>() {
    inner class PlaylistViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)

    private val diffCallback = object : DiffUtil.ItemCallback<CarInfo>(){
        override fun areItemsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
            return oldItem.id   == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var carinfolist : List<CarInfo>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private var onItemClickListener: ((CarInfo) -> Unit)? = null

    fun setOnItemClickListener(listener: (CarInfo) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vp_carinfo,parent,false))
    }

    override fun getItemCount(): Int {
        return carinfolist.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val data = carinfolist[position]
        holder.itemView.apply {
            tv_registernum.text = data.carRegistrationNumber
            tv_branddata.text = data.brand
            tv_modeldata.text = data.model
            tv_yeardata.text = data.year.toString()
            tv_carplatedata.text = data.carPlateNumber

            cd_remove.setOnClickListener {
                onItemClickListener?.let {
                    it(data)
                }
            }
        }
    }

}