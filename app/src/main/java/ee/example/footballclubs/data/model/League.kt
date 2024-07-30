package ee.example.footballclubs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
/**
 * RoomDatabase entity class will also be used with UI
 */
data class League(
    @PrimaryKey(autoGenerate = false)
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String,
    val strLeagueLogo: String? = null
)
