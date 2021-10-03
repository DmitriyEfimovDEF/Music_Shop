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
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.friden.ru.musicshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val goodsHashmap = HashMap<String, Double>()
    lateinit var dataBinding: ActivityMainBinding
    private val price: Double = 0.0
    var quantity: Int = 0
    private val viewModelMusicShop by viewModels<ViewModelMusicShop>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModelMusicShop
        createSpinner()
        createHashMap()
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

    private fun createHashMap() {
        with(goodsHashmap) {
            put("guitar", 500.0)
            put("drums", 1500.0)
            put("keyboard", 1000.0)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (dataBinding.spinner.selectedItem.toString()) {
            "Guitar" -> {
                dataBinding.imageView2.setImageResource(R.drawable.guitar)
                viewModelMusicShop._goodsName.value = "Guitar"
                viewModelMusicShop.getOrderPrice()
            }
            "Drums" -> {
                dataBinding.imageView2.setImageResource(R.drawable.drums)
                viewModelMusicShop._goodsName.value = "Drums"
                viewModelMusicShop.getOrderPrice()

            }
            "Keyboard" -> {
                dataBinding.imageView2.setImageResource(R.drawable.keyboard)
                viewModelMusicShop._goodsName.value = "Keyboard"
                viewModelMusicShop.getOrderPrice()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    fun addToCart() {
        Log.d("TAG", "REaDY")
        val intent: Intent = Intent(this, OrderActivity::class.java).apply {
            putExtra("userName", viewModelMusicShop.userName)
            putExtra("goodsName", viewModelMusicShop.goodsName.value)
            putExtra("quantity", "${viewModelMusicShop.quantity.value}")
            putExtra("price", viewModelMusicShop.price)
            putExtra("orderPrice", "${viewModelMusicShop.totalPrice.value}")
        }
        startActivity(intent)
    }
}
