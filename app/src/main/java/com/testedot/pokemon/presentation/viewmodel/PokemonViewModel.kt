package com.testedot.pokemon.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testedot.pokemon.data.model.PokemonApiResponse
import com.testedot.pokemon.data.util.Resource
import com.testedot.pokemon.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val application: Application, private val getPokemonUseCase: GetPokemonListUseCase) : AndroidViewModel(application) {

    val pokemonList: MutableLiveData<Resource<PokemonApiResponse>> = MutableLiveData()

    fun getPokemonList(offset: Int, limit: Int) = viewModelScope.launch(Dispatchers.IO) {
        pokemonList.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getPokemonUseCase.execute(offset, limit)
                pokemonList.postValue(apiResult)
            }
        } catch (e: Exception) {
            pokemonList.postValue(Resource.Error(e.message.toString()))
        }

    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context?): Boolean {
        var result = false
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

}