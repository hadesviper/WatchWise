package com.herald.watchwise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.herald.watchwise.data.local.entities.EntityMovie

/**
 * Room database class for the Watch Later feature, which manages a local database of movies that the user wants to watch later.
 */
@Database(
    entities = [EntityMovie::class],
    version = 1,
    exportSchema = false
)
abstract class WatchLaterDatabase : RoomDatabase() {
    /**
     * Returns a [WatchLaterDao] object, which provides methods for interacting with the local database.
     */
    abstract fun watchLaterDao(): WatchLaterDao

    companion object {
        const val DB_Name = "WatchLaterDB"
    }
}