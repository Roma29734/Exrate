package com.example.exrate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exrate.data.model.entity.ListSupportedEntity
import com.example.exrate.databinding.CurrencyAddRowCardBinding

class AddAdapter : RecyclerView.Adapter<AddAdapter.MyViewHolder>(){

    inner class MyViewHolder(val binding: CurrencyAddRowCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listSupported = emptyList<ListSupportedEntity>()

    var clickToCard: ((model: ListSupportedEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CurrencyAddRowCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionList = listSupported[position]

        holder.binding.apply {
            textName.text = positionList.name
            textSymbol.text = positionList.symbol
            card.setOnClickListener {
                clickToCard?.let { it1 -> it1(positionList) }
            }
        }
    }

    override fun getItemCount(): Int {
        return listSupported.size
    }

    fun setData(list: List<ListSupportedEntity>) {
        listSupported = list
        notifyDataSetChanged()
    }
}