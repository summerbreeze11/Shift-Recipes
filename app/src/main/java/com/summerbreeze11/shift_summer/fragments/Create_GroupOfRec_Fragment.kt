package com.summerbreeze11.shift_summer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summerbreeze11.shift_summer.databinding.FragCreateGroupOfRecBinding

class Create_GroupOfRec_Fragment : Fragment() {
    private lateinit var binding: FragCreateGroupOfRecBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragCreateGroupOfRecBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = Create_GroupOfRec_Fragment()
    }
}