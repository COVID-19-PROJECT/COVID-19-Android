package com.gt.quedateencasa.repositories

import android.content.Context
import com.gt.quedateencasa.R
import com.gt.quedateencasa.repositories.models.EmergencyNumberObject

class EmergencyNumbersRepository {
    fun getNumbers(context: Context): List<EmergencyNumberObject> {
        return arrayListOf(
            EmergencyNumberObject(
                context.getString(R.string.title_emergency_number_gt),
                context.getString(R.string.emergency_number_gt),
                R.drawable.ic_gobierno_gt
            ),
            EmergencyNumberObject(
                context.getString(R.string.title_emergency_number_gt),
                context.getString(R.string.emergency_number_gt_2),
                R.drawable.ic_gobierno_gt
            ),
            EmergencyNumberObject(
                context.getString(R.string.title_emergency_number_cbm_gt),
                context.getString(R.string.emergency_number_cbm_gt),
                R.drawable.ic_cbm_gt
            ),
            EmergencyNumberObject(
                context.getString(R.string.title_emergency_number_cvb_gt),
                context.getString(R.string.emergency_number_cvb_gt),
                R.drawable.ic_cvb_gt
            ),
            EmergencyNumberObject(
                context.getString(R.string.title_emergency_number_pnc_gt),
                context.getString(R.string.emergency_number_pnc_gt),
                R.drawable.ic_pnc_gt
            )
        )
    }
}