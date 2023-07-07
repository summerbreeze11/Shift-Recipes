package com.summerbreeze11.shift_summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.summerbreeze11.shift_summer.databinding.ActivityMainBinding
import com.summerbreeze11.shift_summer.fragments.Create_GroupOfRec_Fragment
import com.summerbreeze11.shift_summer.fragments.Create_Rec_Fragment
import com.summerbreeze11.shift_summer.fragments.Saved_Rec_Fragment
import com.summerbreeze11.shift_summer.utils.openFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBottomNavClick()
        openFragment(Create_Rec_Fragment.newInstance())
    }

    private fun onBottomNavClick() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.create_rec -> {
                    openFragment(Create_Rec_Fragment.newInstance())
                }
                R.id.create_group_of_rec -> {
                    openFragment(Create_GroupOfRec_Fragment.newInstance())
                }
                R.id.saved_rec -> {
                    openFragment(Saved_Rec_Fragment.newInstance())
                }
            }
            true
        }
    }
}