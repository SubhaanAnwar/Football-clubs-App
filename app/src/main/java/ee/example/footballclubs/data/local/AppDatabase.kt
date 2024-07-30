package ee.example.footballclubs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ee.example.footballclubs.data.model.League

// The app's primary database section configures data organization and storage, including messages and user data, using the Room library as a unique tool.

@Database(entities = [League::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun leagueDao(): LeagueDao

    companion object {
        const val DATABASE_NAME = "football_db"
    }
}