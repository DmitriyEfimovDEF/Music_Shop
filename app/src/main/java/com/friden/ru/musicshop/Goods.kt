package com.friden.ru.musicshop

import androidx.annotation.DrawableRes

enum class Goods(val text: String, val price: Double, @DrawableRes val iconDrawable: Int) {
    GUITAR("Guitar", 500.0, R.drawable.guitar),
    DRUMS("Drums", 1500.0, R.drawable.keyboard),
    KEYBOARD("Keyboard", 1000.0, R.drawable.keyboard)
}