package com.example.kryptoparauygulamas.service

import com.example.kryptoparauygulamas.model.CryptoModel
import retrofit2.http.GET
import retrofit2.Call




interface CryptoAPI {



        @GET("https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json")
        fun getData(): Call<List<CryptoModel>>


    }