package com.gt.quedateencasa.views.main.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.quedateencasa.models.Notice.NoticeObject
import com.gt.quedateencasa.models.Notice.NoticeService

class HomeViewModel : ViewModel() {

    private val noticeService by lazy {
        NoticeService()
    }

    fun getNotices(context: Context?): LiveData<ArrayList<NoticeObject>> {
        val mutableLiveData = MutableLiveData<ArrayList<NoticeObject>>()
        context?.let {
            mutableLiveData.postValue(noticeService.findNotices())
        }
        return mutableLiveData
    }
}