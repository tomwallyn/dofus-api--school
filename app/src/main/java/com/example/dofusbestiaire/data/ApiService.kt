package com.example.appnico.data

import com.example.appnico.models.Monsters
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("monsters")
    suspend fun getMonsters(): Response<List<Monsters>>
}