package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gt.quedateencasa.R
import com.gt.quedateencasa.repositories.models.EmergencyNumberObject
import kotlinx.android.synthetic.main.item_emergency_number.view.*

class EmergencyNumberAdapter :
    RecyclerView.Adapter<EmergencyNumberAdapter.ItemEmergencyNumberViewHolder>() {

    private val listNumber = arrayListOf<EmergencyNumberObject>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemEmergencyNumberViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_emergency_number, parent, false)
        return ItemEmergencyNumberViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listNumber.size
    }

    override fun onBindViewHolder(holder: ItemEmergencyNumberViewHolder, position: Int) {
        holder.bind(listNumber[position], position)
    }

    fun updateItems(list: List<EmergencyNumberObject>) {
        listNumber.clear()
        listNumber.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemEmergencyNumberViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: EmergencyNumberObject, position: Int) {
            v.txt_emergency_number.text = item.number
            v.txt_emergency_title.text = item.name
            v.img_logo.setImageResource(item.resource)
        }
    }
}