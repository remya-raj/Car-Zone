package com.example.carzone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                val name = it.name
                val providerName = it.provider_name
                Glide
                    .with(itemView)
                    .load(it.image)
                    .centerCrop()
                    .into(ivOffer)

                ivOffer.setOnClickListener {
                    showDialog(itemView.context, name, providerName)
                }
            }
        }

    }

    private fun showDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton("Close"){dialogInterface , which ->
            Toast.makeText(context,"Alert Closed",Toast.LENGTH_SHORT).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
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