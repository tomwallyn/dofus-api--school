package com.example.dofusbestiaire.data

import com.example.dofusbestiaire.models.Monsters
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("monsters")
    suspend fun getMonsters(): Response<List<Monsters>>
}