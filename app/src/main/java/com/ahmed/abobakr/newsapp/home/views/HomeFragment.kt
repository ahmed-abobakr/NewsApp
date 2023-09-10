package com.ahmed.abobakr.newsapp.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmed.abobakr.newsapp.base.BaseFragment
import com.ahmed.abobakr.newsapp.base.UiState
import com.ahmed.abobakr.newsapp.databinding.FragmentHomeBinding
import com.ahmed.abobakr.newsapp.home.view_models.HomeUiState
import com.ahmed.abobakr.newsapp.home.view_models.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    private var page = 1
    private lateinit var adapter: TopArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        adapter  = TopArticlesAdapter{
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.rvNewsList.adapter = adapter

        viewModel.getHeaderTopNews(page)
    }

    override fun render(state: UiState) {
        when(state){
            is UiState.Loading -> {}
            is HomeUiState.Success -> {
                adapter.submitList(state.result)
            }
            is UiState.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
        }
    }
}