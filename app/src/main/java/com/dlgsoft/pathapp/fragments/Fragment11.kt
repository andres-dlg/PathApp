package com.dlgsoft.pathapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dlgsoft.pathapp.R

class Fragment11: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_11, container, false)
    }

    companion object {
        const val TAG = "Fragment11"
        fun newInstance() = Fragment11()
    }
}