package com.demo.newappdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.newappdemo.R
import com.demo.newappdemo.data.Repository
import com.demo.newappdemo.model.NewsResponseModel
import com.demo.newappdemo.utils.Constant
import com.demo.newappdemo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsListingFragmentViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var mContext = application

    private val _newsList =
        MutableLiveData<NetworkResult<NewsResponseModel>>(NetworkResult.Loading())
    val newsList: LiveData<NetworkResult<NewsResponseModel>> get() = _newsList

    fun getNewsData(
    ) {
        viewModelScope.launch {
            try {
                _newsList.value = NetworkResult.Loading()
                val response = repository.remote.getNews()
                response.body()?.let {
                    if (it.status == Constant.API_RESPONSE_STATUS.OK) {
                        it.articles?.let { data ->
                            repository.local.insertNewsList(data)
                            _newsList.value = NetworkResult.Success(
                                it
                            )
                        }
                    } else {
                        setErrorMessage(it.message)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e.localizedMessage)
                setErrorMessage(mContext.getString(R.string.something_went_wrong))
            }
        }
    }

    fun getNewsDataLocal(
    ) {
        viewModelScope.launch {
            try {
                _newsList.value = NetworkResult.Loading()
                val newsList = repository.local.getNewsList()
                _newsList.value = NetworkResult.Success(
                    NewsResponseModel(status = Constant.API_RESPONSE_STATUS.OK, articles = newsList)
                )

            } catch (e: Exception) {
                Timber.e(e.localizedMessage)
                setErrorMessage(mContext.getString(R.string.something_went_wrong))
            }
        }
    }

    private fun setErrorMessage(message: String?) {
        _newsList.value = NetworkResult.Error(message)
    }
}