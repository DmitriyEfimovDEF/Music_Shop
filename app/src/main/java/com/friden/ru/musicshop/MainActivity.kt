package com.friden.ru.musicshop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.friden.ru.musicshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val goodsHashmap = HashMap<String, Double>()
    private lateinit var dataBinding: ActivityMainBinding
    private val viewModelMusicShop by viewModels<ViewModelMusicShop>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModelMusicShop
        createHashMap()
        createSpinner()
        observeData()
    }

    private fun observeData() = viewModelMusicShop.onAddToCartEvent.observe(this){
        addToCart()
    }

    private fun createSpinner() {
        val spinner: Spinner = dataBinding.spinner
        ArrayAdapter.createFromResource(
            this, R.array.spinner_items, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    private fun createHashMap() = Goods.values().forEach {
        goodsHashmap[it.text] = it.price
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Goods.values().find { it.text == dataBinding.spinner.selectedItem.toString() }?.let {
            dataBinding.imageView2.setImageResource(it.iconDrawable)
            viewModelMusicShop.updateGood(it)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    private fun addToCart() {
        Log.d("~~~~TAG", "REaDY")
        val intent: Intent = Intent(this, OrderActivity::class.java).apply {
            putExtra("userName", viewModelMusicShop.userName)
            putExtra("goodsName", viewModelMusicShop.goodsName.value)
            putExtra("quantity", "${viewModelMusicShop.quantity.value}")
            putExtra("price", viewModelMusicShop.goodsName.value?.price)
            putExtra("orderPrice", "${viewModelMusicShop.totalPrice.value}")
        }
        startActivity(intent)
    }
}
