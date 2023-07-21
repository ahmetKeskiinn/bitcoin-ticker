package com.ahmetkeskin.bitcointicker.feature.home.presentation

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
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem


class CurrencyListAdapter(
    private val listener: CurrencyClickListener
) : ListAdapter<CryptoIconItem, CurrencyListAdapter.CityHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_currency,
            parent,
            false
        )
        return CityHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        with(getItem(position)) {
            holder.currencyName.text = this.asset_id
            holder.currencyIcon.loadImage(this.url)
            holder.itemView.setOnClickListener {
                listener.isCurrencyClicked(this)
            }
        }
    }

    inner class CityHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val currencyName: TextView = iv.findViewById(R.id.currencyName)
        val currencyIcon: ImageView = iv.findViewById(R.id.currencyImage)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<CryptoIconItem>() {
    override fun areItemsTheSame(
        oldItem: CryptoIconItem,
        newItem: CryptoIconItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CryptoIconItem,
        newItem: CryptoIconItem
    ): Boolean {
        return oldItem == newItem
    }
}