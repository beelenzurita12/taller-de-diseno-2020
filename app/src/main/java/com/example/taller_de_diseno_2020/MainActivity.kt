    package com.example.taller_de_diseno_2020

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.example.taller_de_diseno_2020.adapters.ProductAdapter
    import com.example.taller_de_diseno_2020.api.Api
    import com.example.taller_de_diseno_2020.entity.MeliSearchResult
    import com.example.taller_de_diseno_2020.entity.SearchResult
    import kotlinx.android.synthetic.main.activity_main.*
    import com.example.taller_de_diseno_2020.utils.hideKeyboard
    import com.google.android.material.snackbar.Snackbar
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response

    class MainActivity : AppCompatActivity() {

        private var adapter = ProductAdapter()
        private lateinit var currentSearch: ArrayList<MeliSearchResult>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            searchAction.setOnClickListener { search(searchText.editText?.text.toString()) }

            productsList.layoutManager = LinearLayoutManager(this)
            productsList.adapter = adapter
        }

        private fun search(q:String){
            hideKeyboard()
            Api().search(q, object : Callback<SearchResult> {

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Snackbar.make(mainContainer, R.string.no_internet, Snackbar.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) =
                    when (response.code()) {
                        in 200..299 -> {
                            currentSearch = response.body()?.results!!
                            setResultValues(response.body()!!)
                        }
                        404 -> Toast.makeText(
                            this@MainActivity,
                            R.string.resource_not_found,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        400 -> Toast.makeText(
                            this@MainActivity,
                            R.string.bad_request,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        in 500..599 -> Toast.makeText(
                            this@MainActivity,
                            R.string.server_error,
                            Toast.LENGTH_LONG
                        )
                            .show()
                        else -> Toast.makeText(
                            this@MainActivity,
                            R.string.unknown_error,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
            })
        }

        private fun setResultValues(body: SearchResult){
            if (body.results.isNotEmpty()) {
                 adapter.productList = body.results
                 adapter.stringSearch = searchText.editText?.text.toString()
                } else {
                adapter.productList = ArrayList()

                Toast.makeText(
                    this@MainActivity,
                    R.string.not_found,
                    Toast.LENGTH_LONG
                )
                    .show()
            }

            adapter.notifyDataSetChanged()
        }
    }
