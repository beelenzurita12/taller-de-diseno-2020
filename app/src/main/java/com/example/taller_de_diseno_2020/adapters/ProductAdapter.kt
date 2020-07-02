package com.example.taller_de_diseno_2020.adapters

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taller_de_diseno_2020.ProductActivity
import com.example.taller_de_diseno_2020.R
import com.example.taller_de_diseno_2020.entity.MeliSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var productList = ArrayList<MeliSearchResult>()
    var stringSearch = String()

    private companion object{
        private const val itemKey : String = "item"
    }

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
                        "")

                Picasso.get()
                    .load(product.thumbnail.replace(
                        "http",
                        "https"))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(holder.itemView.thumbNail)
        }
        holder.itemView.setOnClickListener{
            if(isOnline(holder.itemView.context)){
                val intent = Intent(it.context, ProductActivity::class.java)
                intent.putExtra(itemKey, productList[position].id)
                it.context.startActivity(intent)
            } else {
                Toast.makeText(holder.itemView.context, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun isOnline(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}