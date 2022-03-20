package com.example.githubuser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var company: String,
    var location: String,
    var followers: String,
    var following: String,
    var repository: String,
    var photo: Int
) : Parcelable
