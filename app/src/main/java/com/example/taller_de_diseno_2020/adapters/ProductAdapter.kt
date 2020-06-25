package com.example.taller_de_diseno_2020.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.taller_de_diseno_2020.MainActivity
import com.example.taller_de_diseno_2020.ProductActivity
import com.example.taller_de_diseno_2020.R
import com.example.taller_de_diseno_2020.entity.MeliSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var productList = ArrayList<MeliSearchResult>()
    var stringSearch = String()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productList[position].let { product ->
                holder.itemView.productTitle.text = product.title
                holder.itemView.productPrice.text =
                    holder.itemView.context.getString(
                        R.string.product_price,
                        product.price,
                        ""
                    )

                Picasso.get()
                    .load(product.thumbnail.replace(
                        "http",
                        "https"))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(holder.itemView.thumbNail)
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, ProductActivity::class.java)
            intent.putExtra("item", productList[position].id)
            intent.putExtra("query", stringSearch)
            it.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}