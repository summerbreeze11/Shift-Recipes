package com.summerbreeze11.shift_summer.data

import com.summerbreeze11.shift_summer.model.API_KEY.MY_API_KEY
import com.summerbreeze11.shift_summer.model.CompletionRequest
import com.summerbreeze11.shift_summer.model.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers("Authorization: Bearer $MY_API_KEY")
    @POST("v1/completions")
    suspend fun getCompletions(@Body completionResponse: CompletionRequest) : Response<CompletionResponse>
}