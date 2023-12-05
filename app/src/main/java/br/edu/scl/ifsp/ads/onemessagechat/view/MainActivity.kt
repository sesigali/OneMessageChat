package br.edu.scl.ifsp.ads.onemessagechat.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.onemessagechat.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextRoomName: EditText = findViewById(R.id.editTextMessage)
        val buttonEnterRoom: Button = findViewById(R.id.buttonSend)

        buttonEnterRoom.setOnClickListener {
            val roomName = editTextRoomName.text.toString()
            val chatId = roomName.hashCode().toString()

            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("chatId", chatId)
            startActivity(intent)
        }
    }
}
