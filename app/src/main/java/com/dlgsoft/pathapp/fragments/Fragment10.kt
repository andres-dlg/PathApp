package com.dlgsoft.pathapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dlgsoft.pathapp.R

class Fragment10: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_10, container, false)
    }

    companion object {
        const val TAG = "Fragment10"
        fun newInstance() = Fragment10()
    }
}