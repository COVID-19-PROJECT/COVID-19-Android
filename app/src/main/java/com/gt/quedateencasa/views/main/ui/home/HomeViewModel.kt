package com.gt.quedateencasa.views.main.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.quedateencasa.models.Notice.NoticeService

class HomeViewModel : ViewModel() {

    private val noticeService by lazy {
        NoticeService()
    }

    fun getNotice(context: Context?): LiveData<String> {
        val mutableLiveData = MutableLiveData<String>()
        context?.let {
            mutableLiveData.postValue(noticeService.findNotice())
        }
        return mutableLiveData
    }
}