package com.gt.quedateencasa.views.main.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.fragment_recommendations.*

/**
 * A simple [Fragment] subclass.
 */
class RecommendationsFragment : Fragment() {

    companion object {
        private const val ARG_OBJECT = "object"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommendations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            text_recomendations.text = "Fragmento "+getInt(ARG_OBJECT).toString()
        }
    }

}
