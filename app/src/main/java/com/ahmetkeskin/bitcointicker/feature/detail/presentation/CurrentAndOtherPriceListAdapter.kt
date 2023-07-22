package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.loadImage
import com.ahmetkeskin.bitcointicker.feature.detail.data.response.CurrentAndOtherPriceItem


class CurrentAndOtherPriceListAdapter(
    private val currentAndOtherPriceClickListener: CurrentAndOtherPriceClickListener
) : ListAdapter<CurrentAndOtherPriceItem, CurrentAndOtherPriceListAdapter.CityHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_price_for_other,
            parent,
            false
        )
        return CityHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        with(getItem(position)) {
            holder.otherPrice.text = "${this.rate.toString()} ${this.asset_id_quote}"
            holder.currentImage.loadImage(this.currentItemUrl)
            holder.itemView.setOnClickListener {
                currentAndOtherPriceClickListener.isCurrentAndOtherPriceClicked(this)
            }
        }
    }

    inner class CityHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val otherPrice: TextView = iv.findViewById(R.id.otherPrice)
        val currentImage: ImageView = iv.findViewById(R.id.currentImage)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<CurrentAndOtherPriceItem>() {
    override fun areItemsTheSame(
        oldItem: CurrentAndOtherPriceItem,
        newItem: CurrentAndOtherPriceItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CurrentAndOtherPriceItem,
        newItem: CurrentAndOtherPriceItem
    ): Boolean {
        return oldItem == newItem
    }
}