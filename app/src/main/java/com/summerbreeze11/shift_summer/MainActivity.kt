package com.summerbreeze11.shift_summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.summerbreeze11.shift_summer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBottomNavClick()
    }

    private fun onBottomNavClick() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.create_rec -> {
                    Toast.makeText(this, "create_rec", Toast.LENGTH_SHORT).show()
                }
                R.id.ready_rec -> {
                    Toast.makeText(this, "ready_rec", Toast.LENGTH_SHORT).show()
                }
                R.id.saved_rec -> {
                    Toast.makeText(this, "saved_rec", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}