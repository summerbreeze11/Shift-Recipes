package com.summerbreeze11.shift_summer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summerbreeze11.shift_summer.databinding.FragCreateRecBinding

class Create_Rec_Fragment : Fragment() {
    private lateinit var binding: FragCreateRecBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragCreateRecBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bGenerate.setOnClickListener {
            binding.tvAnswer.text = binding.edRequestText.text
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Create_Rec_Fragment()
    }
}