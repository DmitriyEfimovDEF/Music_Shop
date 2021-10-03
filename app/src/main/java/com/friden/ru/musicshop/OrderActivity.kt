package com.friden.ru.musicshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.friden.ru.musicshop.databinding.OrderActivityBinding

class OrderActivity : AppCompatActivity() {

    lateinit var dataBinding: OrderActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.order_activity)
    }
}