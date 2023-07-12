package com.summerbreeze11.shift_summer.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.summerbreeze11.shift_summer.R
import com.summerbreeze11.shift_summer.constants.DATA
import com.summerbreeze11.shift_summer.databinding.FragCreateRecBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class CreateRecFragment : Fragment() {

    private lateinit var binding: FragCreateRecBinding
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragCreateRecBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etQuestion.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                hideKeyboard()

                binding.txtResponse.text = getString(R.string.waiting_response)

                val question = binding.etQuestion.text.toString().trim()

                if (question.isNotEmpty()) {
                    getResponse(question) { response ->
                        activity?.runOnUiThread {
                            binding.txtResponse.text = response
                        }
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etQuestion.windowToken, 0)
    }

    fun getResponse(question: String, callback: (String) -> Unit) {

        val user_request = " ${getString(R.string.your_request)} $question"
        binding.idTVQuestion.text = user_request
        binding.etQuestion.setText("")

        val requestBody = """
            {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(DATA.URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer ${DATA.API_KEY}")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data", body)
                } else {
                    Log.v("data", "empty")
                }
                val jsonObject = JSONObject(body)
                val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                val textResult = jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance() = CreateRecFragment()
    }
}