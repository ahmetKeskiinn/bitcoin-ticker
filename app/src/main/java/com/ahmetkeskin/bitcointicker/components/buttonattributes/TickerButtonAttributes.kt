package com.ahmetkeskin.bitcointicker.components.buttonattributes

import android.graphics.drawable.Drawable

data class TickerButtonAttributes(
    val background: Drawable,
    val buttonAttributes: ButtonAttributes? = null,
    val clickToChangeAttributes: ClickToChangeAttributes? = null
)