package com.example.exrate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exrate.data.model.latest.LatestModel
import com.example.exrate.data.model.latest.Response
import com.example.exrate.databinding.CurrencyRowCardBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CurrencyRowCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var latestModel: LatestModel? = null

    var showBottomSheetDialog: ((city: Response) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CurrencyRowCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(latestModel != null) {
            val latestPosition = latestModel!!.response[position]

            holder.binding.apply {
                textNameCurrency.text = latestPosition.s
                textRate.text = latestPosition.o
                textDifferencePercent.text = latestPosition.cp
                textDifference.text = latestPosition.ch
                root.setOnClickListener {
                    showBottomSheetDialog?.let { it1 -> it1(latestPosition) }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if(latestModel != null) {
            return latestModel?.response!!.size
        }
        return 0
    }

    fun setData(model: LatestModel) {
        latestModel = model
        notifyDataSetChanged()
    }
}