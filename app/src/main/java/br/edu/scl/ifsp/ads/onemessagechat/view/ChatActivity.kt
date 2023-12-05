package br.edu.scl.ifsp.ads.onemessagechat.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.onemessagechat.R
import br.edu.scl.ifsp.ads.onemessagechat.controller.ChatController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class ChatActivity : AppCompatActivity() {

    private lateinit var editTextMessage: TextInputEditText
    private lateinit var buttonSend: MaterialButton
    private lateinit var textViewChat: MaterialTextView

    private lateinit var chatController: ChatController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSend = findViewById(R.id.buttonSend)
        textViewChat = findViewById(R.id.textViewChat)

        chatController = ChatController(this)

        chatController.getLastMessage().observe(this) { lastMessage ->
            textViewChat.text = lastMessage
        }

        buttonSend.setOnClickListener {
            val message = editTextMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                val chatId = getChatId()
                chatController.sendMessage(chatId, message)
                editTextMessage.text = null
            }
        }
    }

    private fun getChatId(): String {
        val timestamp = System.currentTimeMillis()
        return "chat_${timestamp}_${Math.random()}"
    }
}
