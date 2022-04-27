package com.demo.newappdemo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.demo.newappdemo.databinding.FragmentNewsDetailBinding
import com.demo.newappdemo.utils.loadImageFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val args: NewsDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setOnClick()
    }

    private fun setOnClick() {

        binding.imgNews.setOnClickListener {
            findNavController().navigate(
                NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsImageFragment(
                    args.newsModel
                )
            )
        }
    }

    private fun setData() {
        binding.apply {
            args.newsModel.apply {
                imgNews.loadImageFromUrl(urlToImage)
                txtTitle.text = title
                txtDescription.text = description
                txtContent.text = content
                txtLink.text = url
                txtAuthor.text = author
                txtDate.text = publishedAt
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}