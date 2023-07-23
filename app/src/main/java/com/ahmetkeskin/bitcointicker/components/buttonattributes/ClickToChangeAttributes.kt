package com.ahmetkeskin.bitcointicker.components.buttonattributes

import android.graphics.drawable.Drawable

data class ClickToChangeAttributes(
    val selectedIcon: Drawable?,
    val unselectedIcon: Drawable?,
    val isRight: Boolean = true,
    val isInitSelected: Boolean = false
)
