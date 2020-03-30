package com.gt.quedateencasa.models.EmergencyNumbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.item_emergency_number.view.*

class EmergencyNumberAdapter :
    RecyclerView.Adapter<EmergencyNumberAdapter.ItemEmergencyNumberViewHolder>() {

    private val listNumber = arrayListOf<EmergencyNumberObject>()
    var listener: NumberListener? = null

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
        holder.bind(listNumber[position])
    }

    fun updateItems(list: List<EmergencyNumberObject>) {
        listNumber.clear()
        listNumber.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemEmergencyNumberViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: EmergencyNumberObject) {
            v.txt_emergency_number.text = item.number
            v.txt_emergency_title.text = item.name
            v.img_logo.setImageResource(item.resource)
            v.btn_call.setOnClickListener {
                listener?.onNumberClickListener(item)
            }
        }
    }

    interface NumberListener {
        fun onNumberClickListener(item: EmergencyNumberObject)
    }
}