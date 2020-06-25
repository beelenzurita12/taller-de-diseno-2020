package com.example.taller_de_diseno_2020

import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller_de_diseno_2020.api.Api
import com.example.taller_de_diseno_2020.entity.Item
import com.example.taller_de_diseno_2020.entity.ItemDescription
import com.example.taller_de_diseno_2020.entity.MeliSearchResult
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val itemId : String = intent.getStringExtra("item")

        getProduct(itemId)
        getDescription(itemId)
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
                Snackbar.make(mainContainer, R.string.no_internet, Snackbar.LENGTH_LONG).show()
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
                Snackbar.make(mainContainer, R.string.no_internet, Snackbar.LENGTH_LONG).show()
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
        titleProduct.text = item.title
        priceProduct.text = item.price.toString()

        Picasso.get()
            .load(item.thumbnail.replace(
                "http",
                "https"
            ))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error_outline_black_24dp)
            .into(imageProduct)
    }
}

