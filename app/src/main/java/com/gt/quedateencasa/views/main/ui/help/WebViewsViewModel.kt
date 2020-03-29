package com.gt.quedateencasa.views.main.ui.help

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.quedateencasa.models.PressRelease.PressReleaseService
import com.gt.quedateencasa.models.Recomendation.RecomendationService

class WebViewsViewModel: ViewModel() {

    private val recomendationService by lazy {
        RecomendationService()
    }

    private val pressReleaseService by lazy {
        PressReleaseService()
    }

    fun getRecomendation(position: Int, context: Context?): LiveData<String> {
        val mutableLiveData = MutableLiveData<String>()
        context?.let {
            mutableLiveData.postValue(getSelectionUrl(position))
        }
        return mutableLiveData
    }

    private fun getSelectionUrl(position: Int): String {
        return if (position == 1)
            recomendationService.findRecomendation()
        else
            pressReleaseService.findPressRelease()
    }
}