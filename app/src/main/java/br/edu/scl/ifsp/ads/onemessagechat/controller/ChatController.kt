package br.edu.scl.ifsp.ads.onemessagechat.controller

import ChatModelRoomDaoDatabase
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import br.edu.scl.ifsp.ads.onemessagechat.dao.ChatModelRoomDao
import br.edu.scl.ifsp.ads.onemessagechat.model.ChatModel
import br.edu.scl.ifsp.ads.onemessagechat.model.Constants
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener

class ChatController(private val context: Context) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val chatsReference = firebaseDatabase.getReference(Constants.FIREBASE_CHILD_CHAT)
    private val chatModelRoomDao: ChatModelRoomDao

    init {
        val roomDatabase = Room.databaseBuilder(
            context.applicationContext,
            ChatModelRoomDaoDatabase::class.java,
            "chat_database"
        ).build()

        chatModelRoomDao = roomDatabase.chatModelRoomDao()
    }

    private fun sendMessageToFirebase(chatId: String, message: String) {
        val timestamp = ServerValue.TIMESTAMP
        chatsReference.child(chatId).setValue(ChatModel(chatId, message, timestamp))
    }

    fun subscribeToChat(chatId: String, onDataChange: (ChatModel) -> Unit) {
        chatsReference.child(chatId).orderByChild("timestamp")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    val chat = snapshot.getValue(ChatModel::class.java)
                    chat?.let { onDataChange.invoke(it) }
                }

                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                    // ...
                }
            })
    }

    fun sendMessage(chatId: String, message: String) {
        sendMessageToFirebase(chatId, message)
    }

    fun getLastMessage(): LiveData<String> {
        return MutableLiveData()
    }
}
