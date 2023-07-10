package com.summerbreeze11.shift_summer.model

data class Message(
    val message : String,
    val sentBy : String,
    val timetamp : String
        )
{
    companion object{
        const val SENT_BY_ME = "sent_me"
        const val SENT_BY_BOT = "sent_bot"
    }
}