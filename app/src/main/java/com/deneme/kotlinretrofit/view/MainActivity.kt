package com.deneme.kotlinretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deneme.kotlinretrofit.R
import com.deneme.kotlinretrofit.adapter.RecyclerViewAdapter
import com.deneme.kotlinretrofit.model.CryptoModel
import com.deneme.kotlinretrofit.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener{

    private val BASE_URL="https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>? =null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null

    private var compositeDisposable:CompositeDisposable?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                //7cac1cdc7bb59942b2ae5c118ff91f7267975824
                //https://api.nomics.com/v1
                //https://api.nomics.com/v1/prices?key=7cac1cdc7bb59942b2ae5Fc118ff91f7267975824

        compositeDisposable= CompositeDisposable()


        var layoutManager: RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager


    loadData()

    }

    private fun loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))


    }
        private fun handleResponse(cryptoList: List<CryptoModel>){
            cryptoModels=ArrayList(cryptoList)

            cryptoModels?.let {
                recyclerViewAdapter=RecyclerViewAdapter(it,this@MainActivity)
                recyclerView.adapter=recyclerViewAdapter
            }
        }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Tıklandı :${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}