package com.ahmetkeskin.bitcointicker.base

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    url.let {
        Glide.with(this).load(url).into(this)
    }
}