package br.edu.scl.ifsp.ads.onemessagechat.model

import androidx.room.Entity


@Entity
data class ChatModel(
    val conversationId: String = "",
    val currentMessage: String = "",
    val timestamp: Any? = null
)
