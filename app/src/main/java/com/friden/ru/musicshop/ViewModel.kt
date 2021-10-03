package com.friden.ru.musicshop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMusicShop : ViewModel() {

    var userName: String = ""
    private val _quantity = MutableLiveData(0)
    val quantity: LiveData<Int> = _quantity

    private val _goodsName = MutableLiveData(Goods.GUITAR)
    val goodsName: LiveData<Goods> = _goodsName

     val totalPrice = MediatorLiveData<Double>().apply {
        value = 0.0
        val observer: (Any) -> Unit = {
            value = _goodsName.value?.price?.times(_quantity.value!!)
        }
        addSource(_goodsName, observer)
        addSource(_quantity, observer)
    }

    private val _onAddToCartEvent = MutableLiveData<Unit>()
    val onAddToCartEvent: LiveData<Unit> = _onAddToCartEvent

    fun increaseQuantity() {
        _quantity.value?.let { a -> _quantity.value = a + 1 }
    }

    fun decreaseQuantity() {
        _quantity.value?.let { a ->
            _quantity.value = a - 1
            if (_quantity.value!! < 0) {
                _quantity.value = 0
            }
        }
    }

    fun addToCart() {
        Log.d("~~~~TAG", "REaDY")
        _onAddToCartEvent.value = Unit
    }

    fun updateGood(good: Goods) {
        _goodsName.value = good
    }
}