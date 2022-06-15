package com.example.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.api.ApiConfig
import com.example.githubuser.model.ItemsItem
import com.example.githubuser.model.SearchResponse
import com.example.githubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listuser = MutableLiveData<ArrayList<User>>()
    val listuser: LiveData<ArrayList<User>> = _listuser

    fun search(searchTags: String): Boolean {
        var success = false
        val client = ApiConfig.getApiService().getSearchResult(searchTags)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    setUser(response.body()?.items!!)
                    success = _listuser.value?.size!! > 0
                    Log.d("Riska", "pesan: ${success}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("Riska", "failure: ${t.message}")
            }
        })
        return success
    }

    private fun setUser(items: List<ItemsItem>) {
        val users = arrayListOf<User>()
        for (item in items) {
            val newUser = User(username = item.login, type = item.type, photo = item.avatarUrl)
            users += listOf<User>(newUser)
        }
        _listuser.value = users
    }
}