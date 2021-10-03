package com.friden.ru.musicshop

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ViewModelMusicShop(application: Application) : AndroidViewModel(application) {

    var userName: String = ""
    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity
    val _goodsName = MutableLiveData("Guitar")
    val goodsName: LiveData<String> = _goodsName
    private val _totalPrice = MutableLiveData(0.0)
    val totalPrice: LiveData<Double> = _totalPrice
    var price: Double = 0.0

    fun increaseQuantity() {
        _quantity.value?.let { a -> _quantity.value = a + 1 }
        getOrderPrice()
    }

    fun decreaseQuantity() {
        _quantity.value?.let { a ->
            _quantity.value = a - 1
            if (_quantity.value!! < 0) {
                _quantity.value = 0
            }
        }
        getOrderPrice()
    }

    fun getOrderPrice() {

        when (goodsName.value) {
            "Guitar" -> price = 500.0
            "Drums" -> price = 1500.0
            "Keyboard" -> price = 1000.0
        }
        _totalPrice.value = price * _quantity.value!!
    }


    fun addToCart() {
//        Log.d("TAG", "REaDY")
        val intent: Intent = Intent(getApplication(), OrderActivity::class.java).apply {
            putExtra("userName", userName)
            putExtra("goodsName", goodsName.value)
            putExtra("quantity", "${quantity.value}")
            putExtra("price", price)
            putExtra("orderPrice", "${totalPrice.value}")
        }
        startActivity(getApplication(), intent, Bundle.EMPTY)
    }
}