package com.example.githubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R.id.rv_users
import com.example.githubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    //    private lateinit var rvUsers: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val listUserAdapter = ListUserAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        setUserData()
        showRecyclerList()

//        supportActionBar?.hide()

//        findUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                findUser(query)
//                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

//    private val listUsers: ArrayList<User>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name)
//            val dataUsername = resources.getStringArray(R.array.data_username)
//            val dataCompany = resources.getStringArray(R.array.data_company)
//            val dataLocation = resources.getStringArray(R.array.data_location)
//            val dataPhoto = resources.obtainTypedArray(R.array.data_avatar)
//            val dataFollowers = resources.getStringArray(R.array.data_followers)
//            val dataFollowing = resources.getStringArray(R.array.data_following)
//            val dataRepository = resources.getStringArray(R.array.data_repository)
//            val listUser = ArrayList<User>()
//            for (i in dataName.indices) {
//                val user = User(
//                    dataName[i], dataUsername[i], dataCompany[i], dataLocation[i],
//                    dataFollowers[i], dataFollowing[i],
//                    dataRepository[i], dataPhoto.getResourceId(i, -1)
//                )
//                listUser.add(user)
//            }
//            return listUser
//        }

    private fun showRecyclerList() {

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
//            val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
//            binding.rvUsers.addItemDecoration(itemDecoration)
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }

        binding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        Toast.makeText(this, "Kamu memilih " + user.name, Toast.LENGTH_SHORT).show()
        val moveObject = Intent(this@MainActivity, DetailUser::class.java)
        moveObject.putExtra(DetailUser.EXTRA_USER, user)
        startActivity(moveObject)
    }

    private fun findUser(searchQuery: String) {
        showStart(false)
        showLoading(true)
        val success = mainViewModel.search(searchQuery)
        Log.d("Riska", "failure: ${success}")
        if (!success) {
            showNotFound(true)
            Log.d("Riska", "Not Found!")
        }
        showLoading(false)
    }

    private fun setUserData() {
        mainViewModel.listuser.observe(this) { user ->
            listUserAdapter.setData(user)
            Log.d("Riska", "data: ${user.count()}")
            if (user.count() != 0){
                showNotFound(false)
            }
            else {
                showNotFound(true)
            }
        }
    }

    //    private fun findUser() {
//        showLoading(true)
//        val client = ApiConfig.getApiService()
//        client.enqueue(object : Callback<SearchResponse> {
//            override fun onResponse(
//                call: Call<SearchResponse>,
//                response: Response<SearchResponse>
//            ) {
//                showLoading(false)
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        setUserData(responseBody.items)
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
//
//        val adapter = SearchAdapter(list)
//
//    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showNotFound(isNotFound: Boolean) {
        binding.notFound.visibility = if (isNotFound) View.VISIBLE else View.GONE
    }
    private fun showStart(isStart: Boolean) {
        binding.imageStart.visibility = if (isStart) View.VISIBLE else View.GONE
        binding.textStart.visibility = if (isStart) View.VISIBLE else View.GONE
    }
}