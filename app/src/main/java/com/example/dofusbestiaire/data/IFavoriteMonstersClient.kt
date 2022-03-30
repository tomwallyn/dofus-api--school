package com.example.dofusbestiaire.data

interface IFavoriteMonstersClient {
    fun saveFavoriteMonster(monsterId: Int)
    fun getFavoriteMonsters(): MutableList<String>
    fun deleteFavoriteMonster(monsterId: Int): Int
}