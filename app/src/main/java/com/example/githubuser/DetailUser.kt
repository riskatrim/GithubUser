package com.example.githubuser


import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuser.adapter.SectionsPagerAdapter
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.example.githubuser.model.User
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_detail_user)

        binding.imageButton.setOnClickListener {
            val intent = Intent(this@DetailUser, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        user.followers
        user.following

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        binding.tvItemName.text = user.name
        binding.tvItemUsername.text = user.username
        binding.tvItemCompany.text = user.company
        binding.tvItemLocation.text = user.location
        binding.tvItemFollowers.text = user.followers.toString()
        binding.tvItemFollowing.text = user.following.toString()
        binding.tvItemRepository.text = user.repository.toString()
//        binding.imgItemPhoto.setImageResource(user.photo)

    }



}

