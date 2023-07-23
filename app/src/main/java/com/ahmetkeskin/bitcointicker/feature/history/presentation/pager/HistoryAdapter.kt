package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.extensions.EMPTY
import com.ahmetkeskin.bitcointicker.base.extensions.loadImage
import com.ahmetkeskin.bitcointicker.base.extensions.twoLetterAfterComma
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerListingItem

class HistoryAdapter : ListAdapter<PagerListingItem, HistoryAdapter.CityHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_history,
            parent,
            false
        )
        return CityHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        with(getItem(position)) {
            holder.otherPrice.text =
                "${this.closedRate?.twoLetterAfterComma()} ${this.quoteCurrency}"
            val newDate = this.closedTime?.split(".")?.get(0)?.split("T") ?: listOf(EMPTY, EMPTY)
            holder.dateTime.text = "${newDate[1]} ${newDate[0]}"
            holder.currencyIcon.loadImage(baseCurrencyIcon)
        }
    }

    inner class CityHolder(iv: View) : RecyclerView.ViewHolder(iv) {
        val otherPrice: TextView = iv.findViewById(R.id.otherPrice)
        val currencyIcon: ImageView = iv.findViewById(R.id.currentImage)
        val dateTime: TextView = iv.findViewById(R.id.dateTime)
    }
}

private val diffCallback = object : DiffUtil.ItemCallback<PagerListingItem>() {
    override fun areItemsTheSame(
        oldItem: PagerListingItem,
        newItem: PagerListingItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PagerListingItem,
        newItem: PagerListingItem
    ): Boolean {
        return oldItem == newItem
    }
}
