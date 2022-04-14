package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getSearchResult(
        @Query("q") query: String,
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetailUsers(
        @Path("username") username: String,
    ): Call<User>
}