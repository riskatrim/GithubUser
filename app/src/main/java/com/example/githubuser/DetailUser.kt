package com.example.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailUser : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val backBtn: ImageButton = findViewById(R.id.imageButton)
        backBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@DetailUser, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        var imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        var tvName: TextView = findViewById(R.id.tv_item_name)
        var tvUsername: TextView = findViewById(R.id.tv_item_username)
        var tvCompany: TextView = findViewById(R.id.tv_item_company)
        var tvLocation: TextView = findViewById(R.id.tv_item_location)
        var tvFollowers: TextView = findViewById(R.id.tv_item_followers)
        var tvFollowing: TextView = findViewById(R.id.tv_item_following)
        var tvRepository: TextView = findViewById(R.id.tv_item_repository)

        var followers = user.followers
        var following = user.following

//        tvName.text = user.name
//        tvUsername.text = user.username
//        tvCompany.text = user.company
//        tvLocation.text = user.location
//        tvFollowers.text =
//            (if (followers >= 1000) ((followers / 1000).toString() + "k") else (followers.toString())) + " Followers"
//        tvFollowing.text =
//            (if (following >= 1000) ((following / 1000).toString() + "k") else (following.toString())) + " Following"
//        tvRepository.text = user.repository.toString() + " Repositories"
//        imgPhoto.setImageResource(user.photo)
    }
}

