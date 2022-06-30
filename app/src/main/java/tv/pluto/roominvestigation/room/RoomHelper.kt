package tv.pluto.roominvestigation.room

import android.content.Context
import androidx.room.Room

class RoomHelper {
    companion object {
        fun createDb(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            )
//                .enableMultiInstanceInvalidation()
                .build()
        }
    }
}