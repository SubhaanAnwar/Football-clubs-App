package ee.example.footballclubs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ee.example.footballclubs.data.model.League
import kotlinx.coroutines.flow.Flow


//The Data Access Object (DAO) is a crucial tool for importing and exporting league data, enabling easy addition, editing, searching, and removal of necessary items. */
@Dao
interface LeagueDao {

    //The function inserts a single [League] into the database, replacing any existing league with the same primary key, and can be called from a coroutine or suspending function.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(league: League)

    // The function adds a new league to the database, modifies an existing one with the same main identity, using coroutine or suspending functions in Kotlin.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<League>)

    //Updates an existing [League] in the database.
    @Update
    suspend fun update(league: League)

    // This function searches the database for leagues using a search query, updating results as matching data changes, and returning an endless stream of League objects.

    @Query("SELECT * FROM League WHERE (strLeague LIKE '%' || :query || '%') OR (strLeagueAlternate LIKE '%' || :query || '%')")
    fun searchLeagues(query: String): Flow<List<League>>

    //This method retrieves a League from the database using its ID, returns a Flow if discovered, and keeps you updated on any modifications to the League's database.

    @Query("SELECT * FROM League WHERE idLeague = :id")
    fun getLeagueById(id: String): Flow<League?>
}