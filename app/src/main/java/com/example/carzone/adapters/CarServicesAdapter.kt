package com.example.carzone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carzone.databinding.CarServicesCardBinding
import com.example.carzone.models.Service

class CarServicesAdapter(private val mList: List<Service?>?) : RecyclerView.Adapter<CarServicesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarServicesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){
            mList?.get(position)?.let {
                tvServiceName.text = it.name
                Glide
                    .with(itemView)
                    .load(it.image_url)
                    .centerCrop()
                    .into(ivService)
            }
        }

    }

    override fun getItemCount(): Int {
        if(mList == null)
            return 0
        return mList.size
    }

    class ViewHolder(binding: CarServicesCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvServiceName = binding.tvServiceName
        val ivService = binding.ivService
    }
}