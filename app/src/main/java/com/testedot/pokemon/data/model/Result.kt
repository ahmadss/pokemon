package com.testedot.pokemon.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "pokemon")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
):Serializable