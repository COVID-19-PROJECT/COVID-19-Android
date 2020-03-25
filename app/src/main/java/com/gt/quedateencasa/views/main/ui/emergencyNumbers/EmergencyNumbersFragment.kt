package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gt.quedateencasa.R
import com.gt.quedateencasa.repositories.models.EmergencyNumberObject
import kotlinx.android.synthetic.main.fragment_home.*


class EmergencyNumbersFragment : Fragment() {

    private val emergencyNumbersViewModel by lazy {
        ViewModelProviders.of(this).get(EmergencyNumbersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        getNumbers()
    }
    private fun setupList(){
        list_numbers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        list_numbers.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        list_numbers.itemAnimator = DefaultItemAnimator()
    }
    private fun getNumbers() {
        emergencyNumbersViewModel.getNumbers(context).observe(viewLifecycleOwner, Observer {
            setNumbersInList(it)
        })
    }

    private fun setNumbersInList(list: List<EmergencyNumberObject>) {

    }
}
