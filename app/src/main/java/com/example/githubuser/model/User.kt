package com.example.githubuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String? = null,
    var username: String? = null,
    var company: String? = null,
    var location: String? = null,
    var followers: Int? = null,
    var following: Int? = null,
    var repository: Int? = null,
    var photo: String? = null,
    var type: String? = null
) : Parcelable
