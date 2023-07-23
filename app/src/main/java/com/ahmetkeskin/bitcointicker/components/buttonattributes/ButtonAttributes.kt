package com.ahmetkeskin.bitcointicker.components.buttonattributes

import android.graphics.drawable.Drawable
import android.view.Gravity

data class ButtonAttributes(
    val tickerButtonTextAttributes: TickerButtonTextAttributes? = null,
    val buttonImage: Drawable? = null,
    val buttonImageGravity: Gravity? = null,
)
