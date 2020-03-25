package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.quedateencasa.R
import com.gt.quedateencasa.views.main.models.EmergencyNumberObject

class EmergencyNumbersViewModel : ViewModel() {

    private val emergencyNumbers =
        arrayListOf(
            EmergencyNumberObject("prueba", "prueba", R.drawable.app_title),
            EmergencyNumberObject("prueba", "prueba", R.drawable.app_title),
            EmergencyNumberObject("prueba", "prueba", R.drawable.app_title),
            EmergencyNumberObject("prueba", "prueba", R.drawable.app_title)
        )

    fun getNumbers(): LiveData<List<EmergencyNumberObject>> {
        val mutableLiveData = MutableLiveData<List<EmergencyNumberObject>>()
        mutableLiveData.postValue(emergencyNumbers)
        return mutableLiveData
    }
}