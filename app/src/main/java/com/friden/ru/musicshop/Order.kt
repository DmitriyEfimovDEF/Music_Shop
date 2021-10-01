package com.friden.ru.musicshop

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable

class Order : BaseObservable() {
    val name: String = ""
    val goodsName: String = ""
    private var _quantity: Int = 0
    var quantity: Int = 0
    set(value) {
        field = value
        notifyPropertyChanged(R.id.textView6)
    }
    val price: Double = 0.0
    val orderPrice: Double = 0.0

    fun increaseQuantity(view: View) {
        _quantity++
            notifyPropertyChanged(BR.viewModelOrder)

        Log.d("TAG", "$_quantity  $quantity")
    }

    fun decreaseQuantity(view: View) {
        _quantity--
        if (_quantity < 0) {
            _quantity = 0
        }
        Log.d("TAG", "$_quantity  $quantity")
    }
}