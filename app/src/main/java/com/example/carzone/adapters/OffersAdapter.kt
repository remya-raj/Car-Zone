package com.example.carzone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carzone.databinding.CarServicesCardBinding
import com.example.carzone.databinding.OffersCardBinding
import com.example.carzone.models.Banner
import com.example.carzone.models.Service

class OffersAdapter(private val mList: List<Banner?>?) : RecyclerView.Adapter<OffersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OffersCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){
            mList?.get(position)?.let {
                Glide
                    .with(itemView)
                    .load(it.image)
                    .centerCrop()
                    .into(ivOffer)
            }
        }

    }

    override fun getItemCount(): Int {
        if(mList == null)
            return 0
        return mList.size
    }

    class ViewHolder(binding: OffersCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivOffer = binding.ivOffer
    }
}