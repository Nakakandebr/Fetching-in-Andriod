package com.assignment.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }


    fun getProducts() {
        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiClient.getProducts()
        request.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {

                    var productResponse = response.body()?.products
                    var productsAdapter = ShopAdapter(productResponse ?: emptyList())
                    binding.rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvProducts.adapter = productsAdapter


                    Toast
                        .makeText(
                            baseContext,
                            "fetched ${productList?.size} products",
                            Toast.LENGTH_LONG
                        )
                        .show()


                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }

            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }
        })

    }
}
