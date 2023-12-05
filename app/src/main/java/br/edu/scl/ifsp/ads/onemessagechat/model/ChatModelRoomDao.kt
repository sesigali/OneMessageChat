package br.edu.scl.ifsp.ads.onemessagechat.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.scl.ifsp.ads.onemessagechat.model.ChatModel

@Dao
interface ChatModelRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chatModel: ChatModel)

    @Query("SELECT * FROM chatmodel ORDER BY timestamp DESC LIMIT 1")
    fun getAllChats(): LiveData<List<ChatModel>>
}
