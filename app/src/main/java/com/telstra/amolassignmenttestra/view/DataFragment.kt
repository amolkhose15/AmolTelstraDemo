package com.telstra.amolassignmenttestra.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.telstra.amolassignmenttestra.R

class DataFragment : Fragment() {
    lateinit var mdataRecycleview: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.data_fragment, container, false)
        bindview(view)
        return view
    }

    private fun bindview(view: View) {
    }
}