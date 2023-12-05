package br.edu.scl.ifsp.ads.onemessagechat.model

import androidx.lifecycle.LiveData
import androidx.room.Dao

@Dao
interface ChatModelDao {
    fun createOrUpdateChat(chatModel: ChatModel)
    fun getAllChats(): LiveData<List<ChatModel>>
}

