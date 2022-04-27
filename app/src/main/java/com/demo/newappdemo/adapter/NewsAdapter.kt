package com.demo.newappdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.newappdemo.common.AdapterClickListener
import com.demo.newappdemo.databinding.ItemNewsAdapterBinding
import com.demo.newappdemo.model.NewsResponseModel
import com.demo.newappdemo.utils.loadImageFromUrl

class NewsAdapter(val context: Context, val mListener: AdapterClickListener) :
    RecyclerView.Adapter<NewsAdapter.ServiceProviderViewHolder>() {

    var list = mutableListOf<NewsResponseModel.Article>()

    class ServiceProviderViewHolder(val binding: ItemNewsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceProviderViewHolder {
        return ServiceProviderViewHolder(
            ItemNewsAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ServiceProviderViewHolder, position: Int) {
        val newsModel = list[holder.absoluteAdapterPosition]
        holder.binding.apply {
            newsModel.apply {
                imgNews.loadImageFromUrl(urlToImage)
                txtTitle.text = title
                txtLink.text = url
                txtAuthor.text = author
                txtDate.text = publishedAt

                clMain.setOnClickListener {
                    mListener.onItemClick(it, holder.absoluteAdapterPosition, newsModel)
                }
            }

        }
    }

    override fun getItemCount(): Int = list.size

    fun addData(newList: List<NewsResponseModel.Article>, isNew: Boolean = true) {
        if (isNew) {
            list = newList.toMutableList()
        } else {
            list.addAll(newList)
        }
        // Use DiffUtil when on live data
        notifyDataSetChanged()
    }
}