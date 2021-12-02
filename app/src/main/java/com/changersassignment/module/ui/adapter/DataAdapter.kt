package com.changersassignment.module.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.changersassignment.R
import com.changersassignment.databinding.ItemDogBreedDataBinding
import com.changersassignment.domain.model.BreedData
import com.squareup.picasso.Picasso
import java.util.*


class DataAdapter : RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>() {
    var uiModels = ArrayList<BreedData>()

    fun clearData() {
        uiModels.clear()
        notifyDataSetChanged()
    }

    fun appendData(list: ArrayList<BreedData>) {
        if (list.size > 0) {

            uiModels.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun updateData(list: ArrayList<BreedData>) {
        if (list.size > 0) {

            uiModels.clear()
            uiModels.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): DataAdapterViewHolder {
        val binding: ItemDogBreedDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_dog_breed_data, parent, false
        )
        return DataAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataAdapterViewHolder,
        position: Int
    ) {
        val uiModel = uiModels[position]

        if (Objects.nonNull(uiModel)) {
            holder.vbinding.callback = this
            Picasso.get()
                .load("https://cdn2.thedogapi.com/images/" + uiModel.reference_image_id + ".jpg")
                .into(holder.vbinding.imageView)


        }
    }


    override fun getItemCount(): Int {
        return uiModels.size
    }

    inner class DataAdapterViewHolder(binding: ItemDogBreedDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var vbinding: ItemDogBreedDataBinding

        init {
            vbinding = binding
            vbinding.setCallback(this@DataAdapter)
        }
    }
}