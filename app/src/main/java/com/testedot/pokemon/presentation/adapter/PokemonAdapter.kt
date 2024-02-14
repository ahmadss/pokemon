package com.testedot.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.testedot.pokemon.data.model.Result
import com.testedot.pokemon.databinding.PokemonItemBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    inner class PokemonViewHolder(
        val binding: PokemonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Result) {
            binding.apply {
                tvName.text = pokemon.name

                Glide.with(ivPokemonImage.context)
                    .load("https://img.pokemondb.net/artwork/large/${pokemon.name}.jpg")
                    .into(ivPokemonImage)

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(pokemon)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}