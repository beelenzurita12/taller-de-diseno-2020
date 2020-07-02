package com.example.taller_de_diseno_2020

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.taller_de_diseno_2020.api.Api
import com.example.taller_de_diseno_2020.entity.Item
import com.example.taller_de_diseno_2020.entity.ItemDescription
import com.example.taller_de_diseno_2020.entity.MeliSearchResult
import com.example.taller_de_diseno_2020.utils.notImplementedButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product.menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class ProductActivity : AppCompatActivity() {

    companion object{
        private const val itemKey : String = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        notImplementedButtonsInView()

        val itemId : String = intent.getStringExtra(itemKey)

        getProduct(itemId)
        getDescription(itemId)

        isOnline()
    }

    override fun onResume() {
        super.onResume()
        isOnline()
    }

    override fun onPause() {
        super.onPause()
        isOnline()
    }

    private fun isOnline(){
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if(!isConnected){
            scroll.isVisible = false
            Toast.makeText(this@ProductActivity, R.string.no_internet, Toast.LENGTH_SHORT).show()
        } else {
            scroll.isVisible = true
        }
    }

    private fun setProductInView(body: MeliSearchResult){
        body.let{
            titleProduct.text = body.title
            priceProduct.text = body.price.toString()

            Picasso.get()
                .load(R.drawable.patines)
        }
    }

    private fun getProduct(itemId : String) {
        Api().getItemById(itemId, object : Callback<Item> {

            override fun onFailure(call: Call<Item>, t: Throwable) {
                Toast.makeText(this@ProductActivity, R.string.error, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Item>, response: Response<Item>) =
                when (response.code()) {
                    in 200..299 -> {
                        val item = response.body()!!
                        setProductValues(item)
                    }
                    404 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.resource_not_found,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    400 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.bad_request,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    in 500..599 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.server_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    else -> Toast.makeText(
                        this@ProductActivity,
                        R.string.unknown_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
        })
    }

    private fun getDescription(itemId: String){
        Api().getItemDescription(itemId, object : Callback<ArrayList<ItemDescription>> {

            override fun onFailure(call: Call<ArrayList<ItemDescription>>, t: Throwable) {
                Toast.makeText(
                    this@ProductActivity,
                    R.string.error,
                    Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<ArrayList<ItemDescription>>, response: Response<ArrayList<ItemDescription>>) =
                when (response.code()) {
                    in 200..299 -> {
                        val description = response.body()!!.first().plainText
                        setDescription(description)
                    }
                    404 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.resource_not_found,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    400 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.bad_request,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    in 500..599 -> Toast.makeText(
                        this@ProductActivity,
                        R.string.server_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                    else -> Toast.makeText(
                        this@ProductActivity,
                        R.string.unknown_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
        })
    }

    private fun setDescription(description: String){
        descriptionProduct.text = description
    }

    private fun setProductValues(item: Item){
        val conditionTranslate: String

        if(item.condition == "new"){
            conditionTranslate = "Nuevo"
        } else {
            conditionTranslate = "Usado"
        }

        titleProduct.text = item.title
        priceProduct.text = getString(R.string.product_price, item.price, "")
        conditionProduct.text = getString(R.string.conditionProduct, conditionTranslate, item.soldQuantity)

        Picasso.get()
            .load(item.thumbnail.replace(
                "http",
                "https"
            ))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error_outline_black_24dp)
            .into(imageProduct)
    }

    private fun notImplementedButtonsInView(){
        menu.setOnClickListener{
            notImplementedButton(this)
        }

        heart.setOnClickListener{
            notImplementedButton(this)
        }

        lupa.setOnClickListener{
            notImplementedButton(this)
        }

        marketProduct.setOnClickListener{
            notImplementedButton(this)
        }

        map.setOnClickListener{
            notImplementedButton(this)
        }

        opinionsProducts.setOnClickListener{
            notImplementedButton(this)
        }

        buyButton.setOnClickListener{
            notImplementedButton(this)
        }

        addToShoppingKart.setOnClickListener{
            notImplementedButton(this)
        }
    }
}

