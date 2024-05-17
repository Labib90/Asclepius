package com.dicoding.asclepius.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.data.response.ResponseNew
import com.dicoding.asclepius.data.retrofit.ConfigApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private var _listNews = MutableLiveData<List<ArticlesItem>?>()
    val listNews: LiveData<List<ArticlesItem>?> = _listNews

    private var _loader = MutableLiveData<Boolean>()
    val loader: LiveData<Boolean> = _loader

    init {
        getAllNews()
    }

    private fun getAllNews() {
        _loader.value = true
        val client = ConfigApi.getServiceApi().getNews()
        client.enqueue(object : Callback<ResponseNew> {
            override fun onResponse(call: Call<ResponseNew>, response: Response<ResponseNew>) {
                _loader.value = false
                if (response.isSuccessful) {
                    _listNews.value = response.body()?.articles
                } else {
                    Log.e("NewViewModel", response.message())
                }
            }


            override fun onFailure(call: Call<ResponseNew>, t: Throwable) {
                _loader.value = false
                Log.e("NewViewModel", t.message.toString())
            }

        })
    }

}