package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gt.quedateencasa.R
import com.gt.quedateencasa.views.main.models.EmergencyNumberObject

class EmergencyNumbersViewModel(application: Application) : AndroidViewModel(application) {

    private val emergencyNumbers =
        arrayListOf(
            EmergencyNumberObject(
                application.getString(R.string.title_emergency_number_gt),
                application.getString(R.string.emergency_number_gt),
                R.drawable.ic_gobierno_gt
            ),
            EmergencyNumberObject(
                application.getString(R.string.title_emergency_number_gt),
                application.getString(R.string.emergency_number_gt_2),
                R.drawable.ic_gobierno_gt
            ),
            EmergencyNumberObject(
                application.getString(R.string.title_emergency_number_cbm_gt),
                application.getString(R.string.emergency_number_cbm_gt),
                R.drawable.ic_cbm_gt
            ),
            EmergencyNumberObject(
                application.getString(R.string.title_emergency_number_cvb_gt),
                application.getString(R.string.emergency_number_cvb_gt),
                R.drawable.ic_cvb_gt
            ),
            EmergencyNumberObject(
                application.getString(R.string.title_emergency_number_pnc_gt),
                application.getString(R.string.emergency_number_pnc_gt),
                R.drawable.ic_pnc_gt
            )
        )

    fun getNumbers(): LiveData<List<EmergencyNumberObject>> {
        val mutableLiveData = MutableLiveData<List<EmergencyNumberObject>>()
        mutableLiveData.postValue(emergencyNumbers)
        return mutableLiveData
    }
}