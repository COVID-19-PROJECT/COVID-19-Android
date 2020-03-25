package com.gt.quedateencasa.views.main.ui.emergencyNumbers

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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

    companion object {
        const val REQUEST_PERMISSION_CALL = 1
    }

    private val emergencyNumbersViewModel by lazy {
        ViewModelProviders.of(this).get(EmergencyNumbersViewModel::class.java)
    }

    private val emergencyNumberAdapter by lazy {
        EmergencyNumberAdapter()
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (REQUEST_PERMISSION_CALL == requestCode) {
            val results = grantResults.filter { it != PackageManager.PERMISSION_GRANTED }
            if (grantResults.isEmpty() || results.isNotEmpty()) {
                Toast.makeText(
                    context,
                    getString(R.string.call_permission_request),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun setupList() {
        list_numbers.adapter = emergencyNumberAdapter
        list_numbers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        list_numbers.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        list_numbers.itemAnimator = DefaultItemAnimator()
        emergencyNumberAdapter.listener = object : EmergencyNumberAdapter.NumberListener {
            override fun onNumberClickListener(item: EmergencyNumberObject) {
                callNumber(item.number)
            }
        }
    }

    private fun getNumbers() {
        emergencyNumbersViewModel.getNumbers(context).observe(viewLifecycleOwner, Observer {
            setNumbersInList(it)
        })
    }

    private fun setNumbersInList(list: List<EmergencyNumberObject>) {
        emergencyNumberAdapter.updateItems(list)
    }

    private fun callNumber(number: String) {
        if (hasPermission()) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        } else {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PERMISSION_CALL)
        }
    }

    private fun hasPermission(): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }
}
