package com.example.kryptoparauygulamas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kryptoparauygulamas.R
import com.example.kryptoparauygulamas.adapter.RecyclerViewAdapter
import com.example.kryptoparauygulamas.model.CryptoModel
import com.example.kryptoparauygulamas.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.recyclerView


class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel>?=null
    private var recyclerViewAdapter: RecyclerViewAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        loadData()

    }
    private fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service=retrofit.create(CryptoAPI::class.java)
        val call=service.getData()
        call.enqueue(object:Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter
                        }
                        recyclerViewAdapter= RecyclerViewAdapter(cryptoModels!!,this@MainActivity)

                        //for(cryptoModel:CrpytoModel in cryptoModels!!){
                        //println(cryptoModel.currency)
                        //println(cryptoModel.price)
                        //}

                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        }
        )

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}


