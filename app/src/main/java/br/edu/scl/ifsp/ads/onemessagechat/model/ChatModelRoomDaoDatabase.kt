import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.scl.ifsp.ads.onemessagechat.dao.ChatModelRoomDao
import br.edu.scl.ifsp.ads.onemessagechat.model.ChatModel

@Database(entities = [ChatModel::class], version = 1, exportSchema = false)
abstract class ChatModelRoomDaoDatabase : RoomDatabase() {
    abstract fun chatModelRoomDao(): ChatModelRoomDao
}
