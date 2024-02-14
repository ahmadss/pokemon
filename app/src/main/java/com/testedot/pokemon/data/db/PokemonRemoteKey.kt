package com.testedot.pokemon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class PokemonRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val prevKey: Int? = null,
    val nextKey: Int? = null
)