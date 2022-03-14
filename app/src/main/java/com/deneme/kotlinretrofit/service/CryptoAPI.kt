package com.deneme.kotlinretrofit.service

import com.deneme.kotlinretrofit.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CryptoAPI {

    //7cac1cdc7bb59942b2ae5c118ff91f7267975824
    //https://api.nomics.com/v1/
    // prices?key=7cac1cdc7bb59942b2ae5c118ff91f7267975824
    @GET("prices?key=7cac1cdc7bb59942b2ae5c118ff91f7267975824")
     fun getData(): Observable<List<CryptoModel>>



//https://api.nomics.com/v1/prices?key=7cac1cdc7bb59942b2ae5c118ff91f7267975824

}