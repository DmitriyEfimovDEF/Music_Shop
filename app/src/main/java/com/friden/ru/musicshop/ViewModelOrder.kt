package com.friden.ru.musicshop

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelOrder: ViewModel() {

    private var _order: MutableLiveData<Order> = MutableLiveData(Order())

    val order: LiveData<Order>
        get() = _order

}