package com.testedot.pokemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testedot.pokemon.data.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase(){

    abstract fun getPokemonDao(): PokemonDao
}