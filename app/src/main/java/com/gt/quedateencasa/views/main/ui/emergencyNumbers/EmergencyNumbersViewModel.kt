package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gt.quedateencasa.models.EmergencyNumbers.EmergencyNumberObject
import com.gt.quedateencasa.models.EmergencyNumbers.EmergencyNumbersRepository

class EmergencyNumbersViewModel(application: Application) : AndroidViewModel(application) {

    private val emergencyNumbersRepository by lazy {
        EmergencyNumbersRepository()
    }

    fun getNumbers(context: Context?): LiveData<List<EmergencyNumberObject>> {
        val mutableLiveData = MutableLiveData<List<EmergencyNumberObject>>()
        context?.let {
            mutableLiveData.postValue(emergencyNumbersRepository.getNumbers(it))
        }
        return mutableLiveData
    }
}