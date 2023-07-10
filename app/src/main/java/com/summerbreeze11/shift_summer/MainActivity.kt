package com.summerbreeze11.shift_summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.summerbreeze11.shift_summer.databinding.ActivityMainBinding
import com.summerbreeze11.shift_summer.fragments.CreateGroupOfRecFragment
import com.summerbreeze11.shift_summer.fragments.CreateRecFragment
import com.summerbreeze11.shift_summer.fragments.SavedRecFragment
import com.summerbreeze11.shift_summer.utils.openFragment
import com.summerbreeze11.shift_summer.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBottomNavClick()
        openFragment(CreateRecFragment.newInstance())
    }

    private fun onBottomNavClick() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.create_rec -> {
                    openFragment(CreateRecFragment.newInstance())
                }
                R.id.create_group_of_rec -> {
                    openFragment(CreateGroupOfRecFragment.newInstance())
                }
                R.id.saved_rec -> {
                    openFragment(SavedRecFragment.newInstance())
                }
                else -> {
                    showToast(R.string.error.toString())
                }
            }
            true
        }
    }
}