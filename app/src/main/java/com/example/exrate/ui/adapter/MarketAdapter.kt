package com.example.exrate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.exrate.R
import com.example.exrate.data.model.latest.Response
import com.example.exrate.databinding.CardCurrencyMarketBinding

class MarketAdapter: RecyclerView.Adapter<MarketAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: CardCurrencyMarketBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var list = emptyList<Response>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardCurrencyMarketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.apply {
            binding.textValue.text = model.s
            binding.textCourse.text = model.o
            binding.textProgress.text = model.cp
            if(model.cp[0] == '-') {
                val colorRound = ContextCompat.getColor(holder.itemView.context, R.color.blackRed)
                val colorAccent = ContextCompat.getColor(holder.itemView.context, R.color.whiteRed)
                binding.cardViewProgress.setCardBackgroundColor(colorRound)
                binding.imageViewProgress.setImageResource(R.drawable.ic_arrow_down)
                binding.imageViewProgress.setColorFilter(colorAccent)
                binding.textProgress.setTextColor(colorAccent)
            } else {
                val colorRound = ContextCompat.getColor(holder.itemView.context, R.color.blackGreen)
                val colorAccent = ContextCompat.getColor(holder.itemView.context, R.color.whiteGreen)
                binding.cardViewProgress.setCardBackgroundColor(colorRound)
                binding.imageViewProgress.setImageResource(R.drawable.ic_arrow_up)
                binding.imageViewProgress.setColorFilter(colorAccent)
                binding.textProgress.setTextColor(colorAccent)
            }
        }

    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setItem(list: List<Response>) {
        this.list = list
        notifyDataSetChanged()
    }
}
