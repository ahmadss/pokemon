package com.testedot.pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testedot.pokemon.databinding.FragmentHomeBinding
import com.testedot.pokemon.presentation.adapter.PokemonAdapter
import com.testedot.pokemon.presentation.viewmodel.PokemonViewModel
import com.testedot.pokemon.data.util.Resource

class HomeFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var pokemonAdapter: PokemonAdapter


    private var limit = 10

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var offset = 0
//    private var prev = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        pokemonAdapter = (activity as MainActivity).pokemonAdapter

        pokemonAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_pokemon", it)
            }

            if(it.url!=null) {
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailFragment,
                    bundle
                )
            }
        }
        initRecycleView()

        viewPokemonList()

    }

    private fun viewPokemonList() {
        viewModel.getPokemonList(offset, limit)
        viewModel.pokemonList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgress()
                    response.data?.let {
//                        Log.i("MYTAG", "test ${it..toList()}")
                        pokemonAdapter.differ.submitList(it.results.toList())
                        Log.d("next ", it.next)
                        var newStrg= it.next
                        val mString = newStrg!!.split("offset").toTypedArray()
                        val moffset = mString[1]!!.split("&")
                        offset = moffset[0].replace("=","").toInt()
                        Log.d("offset y ", offset.toString())
//                        offset = it.next

//                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgress()
                    response.message?.let {
                        Toast.makeText(activity, "An error occured : $it", Toast.LENGTH_LONG).show()
                    }

                }

                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> TODO()
            }

        })


    }


    private fun initRecycleView() {
        fragmentHomeBinding.rvPokemons.apply {
            adapter = pokemonAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(this@HomeFragment.onScrollListener)
        }
    }


    private fun showProgress() {
        isLoading = true
        fragmentHomeBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        isLoading = false
        fragmentHomeBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if(dy > 0) {
                val layoutManager = fragmentHomeBinding.rvPokemons.layoutManager as LinearLayoutManager
                val sizeOfTheCurrentList = layoutManager.itemCount
                val visibleItems = layoutManager.childCount
                val topPosition = layoutManager.findFirstVisibleItemPosition()

                val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
                val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
                if (shouldPaginate) {
//                    page++
                    viewModel.getPokemonList(offset, limit)
                    isScrolling = false

                }
            }

        }
    }


}