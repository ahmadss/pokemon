package com.testedot.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.testedot.pokemon.databinding.ActivityMainBinding
import com.testedot.pokemon.presentation.adapter.PokemonAdapter
import com.testedot.pokemon.presentation.viewmodel.PokemonViewModel
import com.testedot.pokemon.presentation.viewmodel.PokemonViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: PokemonViewModelFactory
    @Inject
    lateinit var pokemonAdapter: PokemonAdapter

    lateinit var viewModel: PokemonViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
            .get(PokemonViewModel::class.java)
    }
}