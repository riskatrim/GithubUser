package com.example.githubuser

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.R.id.rv_users

class MainActivity : AppCompatActivity() {

    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(rv_users)
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataUsername = resources.getStringArray(R.array.data_username)
            val dataCompany = resources.getStringArray(R.array.data_company)
            val dataLocation = resources.getStringArray(R.array.data_location)
            val dataPhoto = resources.obtainTypedArray(R.array.data_avatar)
            val dataFollowers = resources.getStringArray(R.array.data_followers)
            val dataFollowing = resources.getStringArray(R.array.data_following)
            val dataRepository = resources.getStringArray(R.array.data_repository)
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(
                    dataName[i], dataUsername[i], dataCompany[i], dataLocation[i],
                    dataFollowers[i], dataFollowing[i],
                    dataRepository[i], dataPhoto.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter

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
}