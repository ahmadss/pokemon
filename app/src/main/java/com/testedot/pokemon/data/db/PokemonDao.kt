package com.testedot.pokemon.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testedot.pokemon.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Result)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<Result>)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): Flow<List<Result>>

//    @Query("SELECT * FROM pokemon " +"INNER JOIN remote_key ON remote_key.name = pokemon.name " +
//        "ORDER BY nextKey"
//    )
//    fun getAllPokemonsAsPagingSource(): PagingSource<Int, Result>

}