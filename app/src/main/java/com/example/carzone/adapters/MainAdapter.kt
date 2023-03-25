package com.example.carzone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carzone.databinding.SectionGarageCarBinding
import com.example.carzone.databinding.SectionOffersBinding
import com.example.carzone.databinding.SectionServicesBinding
import com.example.carzone.models.Feed

class MainAdapter(context: Context, list: List<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val GARAGE_CAR = 1
        const val SERVICES = 2
        const val BANNERS = 3
    }

    private val mContext: Context = context
    var list: List<Feed> = list

    private inner class SectionGarageViewHolder(binding: SectionGarageCarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivCar = binding.ivCar
        val tvName = binding.tvName
        val tvNumber = binding.tvNumber
        val tvType = binding.tvType

        fun bind(position: Int) {
            val data = list[position]
            tvName.text = data.car_name
            tvNumber.text = data.car_reg_no
            tvType.text = data.car_type

            Glide
                .with(mContext)
                .load(data.image_url)
                .centerCrop()
                .into(ivCar)
        }
    }

    private inner class SectionServicesViewHolder(binding: SectionServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rvCarServices = binding.rvCarServices

        fun bind(position: Int) {
            val data = list[position]
            val carServiceLayoutManager = GridLayoutManager(mContext, 4)
            val carServiceAdapter = CarServicesAdapter(data.services)
            rvCarServices.layoutManager = carServiceLayoutManager
            rvCarServices.adapter = carServiceAdapter
        }
    }

    private inner class SectionOffersViewHolder(binding: SectionOffersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rvOffers = binding.rvOffers

        fun bind(position: Int) {
            val data = list[position]
            val offerLayoutAdapter = LinearLayoutManager(mContext)
            val offersAdapter = OffersAdapter(data.banners)
            rvOffers.layoutManager = offerLayoutAdapter
            rvOffers.adapter = offersAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType) {
            GARAGE_CAR -> {
                val binding = SectionGarageCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SectionGarageViewHolder(binding)
            }
            SERVICES -> {
                val binding = SectionServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SectionServicesViewHolder(binding)
            }
            else -> {
                val binding = SectionOffersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SectionOffersViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(list[position].component_position) {
            GARAGE_CAR -> {
                (holder as SectionGarageViewHolder).bind(position)
            }
            SERVICES -> {
                (holder as SectionServicesViewHolder).bind(position)
            }
            else -> {
                (holder as SectionOffersViewHolder).bind(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].component_position
    }
}