package com.dlgsoft.pathapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dlgsoft.pathapp.OnClickListener
import com.dlgsoft.pathapp.R

class Fragment4A: Fragment() {

    private lateinit var listener: OnClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_4a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<Button>(R.id.btn_next)
        //btnNext.setOnClickListener { listener.navigateToNextFragment(false) }
        btnNext.setOnClickListener { listener.navigateToNextFragmentFork4() }
    }

    companion object {
        const val TAG = "Fragment4A"
        fun newInstance() = Fragment4A()
    }
}