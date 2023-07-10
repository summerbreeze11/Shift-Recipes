package com.summerbreeze11.shift_summer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.summerbreeze11.shift_summer.databinding.ActivityMainBinding
import com.summerbreeze11.shift_summer.fragments.CreateGroupOfRecFragment
import com.summerbreeze11.shift_summer.fragments.CreateRecFragment
import com.summerbreeze11.shift_summer.fragments.SavedRecFragment
import com.summerbreeze11.shift_summer.utils.openFragment
import com.summerbreeze11.shift_summer.utils.showToast
import com.summerbreeze11.shift_summer.model.Message
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.summerbreeze11.shift_summer.databinding.FragCreateRecBinding
import com.summerbreeze11.shift_summer.model.ChatGptViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var _binding : FragCreateRecBinding
    private lateinit var chatGptViewModel: ChatGptViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragCreateRecBinding.inflate(layoutInflater)
        val binding = _binding.root
        //onBottomNavClick()
        openFragment(CreateRecFragment.newInstance())
        setContentView(binding)
        chatGptViewModel = ViewModelProvider(this)[ChatGptViewModel::class.java]
        _binding.bGenerate.setOnClickListener {
            val question = _binding.edRequestText.text.toString()
            val currentTimestamp = chatGptViewModel.getCurrentTimestamp()
            // Добавляем вопрос в TextView
            appendTextToTextView("$question\n", currentTimestamp)

            // Очищаем поле ввода сообщения
            _binding.edRequestText.setText("")

            // Вызываем API и обрабатываем ответ
            chatGptViewModel.callApi(question) { response ->
                // Добавляем ответ в TextView
                appendTextToTextView("${response.text}\n", response.timestamp)
            }
        }
    }
    private fun appendTextToTextView(text: String, timestamp: String) {
        val formattedText = "$timestamp - $text"

        // Получаем текущий текст из TextView
        val currentText = _binding.edRequestText.text.toString()

        // Объединяем старый текст с новым текстом
        val newText = currentText + formattedText

        // Устанавливаем новый текст в TextView
        _binding.tvAnswer.text = newText
    }
    private fun onBottomNavClick() {
        binding.bNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.create_rec -> CreateRecFragment.newInstance()
                R.id.create_group_of_rec -> CreateGroupOfRecFragment.newInstance()
                R.id.saved_rec -> SavedRecFragment.newInstance()
                else -> {
                    showToast(R.string.error.toString())
                    return@setOnItemSelectedListener false
                }
            }

            openFragment(fragment)
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }