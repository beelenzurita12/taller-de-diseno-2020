package com.example.taller_de_diseno_2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taller_de_diseno_2020.entity.MeliSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        //getProduct()
        //setProductInView(getProduct())
    }

    private fun setProductInView(body: MeliSearchResult){
        body.let{
            titleProduct.text = body.title
            priceProduct.text = body.price.toString()

            Picasso.get()
                .load(R.drawable.patines)
    }
    }

    private fun getProduct(): MeliSearchResult {
        return MeliSearchResult(
            id = "abc",
            site_id = "MLA",
            title = "Patin artístico de bota profesional extensible",
            price = 11.999,
            availableQuantity = 120,
            condition = "new",
            soldQuantity = 15,
            permalink = "",
            thumbnail = ""
            )
    }
}
