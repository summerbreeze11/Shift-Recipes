package com.summerbreeze11.shift_summer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summerbreeze11.shift_summer.databinding.FragSavedRecBinding


class Saved_Rec_Fragment : Fragment() {
    private lateinit var binding: FragSavedRecBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragSavedRecBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = Saved_Rec_Fragment()
    }
}