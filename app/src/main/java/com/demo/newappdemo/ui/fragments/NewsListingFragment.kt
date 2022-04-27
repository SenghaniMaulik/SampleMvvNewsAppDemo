package com.demo.newappdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.newappdemo.R
import com.demo.newappdemo.adapter.NewsAdapter
import com.demo.newappdemo.databinding.FragmentNewsListingBinding
import com.demo.newappdemo.model.NewsResponseModel
import com.demo.newappdemo.utils.NetworkResult
import com.demo.newappdemo.utils.checkForInternetConnection
import com.demo.newappdemo.utils.toast
import com.demo.newappdemo.viewmodel.NewsListingFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsListingFragment : Fragment() {

    private var _binding: FragmentNewsListingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListingFragmentViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentNewsListingBinding.inflate(inflater, container, false)
            setData()
        }
        return _binding?.root?:binding.root
    }

    private fun setData() {
        if (requireContext().checkForInternetConnection()) {
            viewModel.getNewsData()
        } else {
            getString(R.string.no_internet).toast(requireContext())
        }
        setupNewsAdapter()
        setOnClicks()
        setObserver()
    }


    private fun setOnClicks() {
    }

    private fun setupNewsAdapter() {
        newsAdapter = NewsAdapter(requireContext()) { `view`, `pos`, `object` ->
            when (view.id) {
                R.id.clMain -> {
                    val data = `object` as NewsResponseModel.Article
                    findNavController().navigate(
                        NewsListingFragmentDirections.actionFragmentNewsListingToNewsDetailFragment(
                            data
                        )
                    )
                }
            }
        }
        binding.rvNewsList.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setObserver() {
        viewModel.newsList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar(true)
                }
                is NetworkResult.Success -> {
                    showProgressBar(false)
                    it.data?.articles?.let { articles ->
                        newsAdapter.addData(articles)
                    }
                }
                is NetworkResult.Error -> {
                    it.message?.toast(requireContext())
                    showProgressBar(false)
                }
            }
        }
    }

    private fun showProgressBar(isShow: Boolean) {
        binding.progressCircular.isVisible = isShow
        binding.rvNewsList.isVisible = !isShow
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}