package com.changersassignment.module.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.changersassignment.R
import com.changersassignment.databinding.SpinnerTextBinding
import com.changersassignment.domain.model.BreedData

class CustomSpinner(
    context: Context,
    list: List<BreedData>
) : ArrayAdapter<BreedData>(
    context,
    ViewHolder.LAYOUT,
    list
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    private fun onBindView(parent: ViewGroup, position: Int): View {
         val model = getItem(position)
        val dataBinding: SpinnerTextBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            ViewHolder.LAYOUT,
            parent,
            false
        )

        dataBinding.model = model

        return dataBinding.root
    }


    private class ViewHolder {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.spinner_text
        }
    }
}
