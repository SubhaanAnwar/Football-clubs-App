package ee.example.footballclubs.data

import ee.example.footballclubs.data.local.LeagueDao
import ee.example.footballclubs.data.model.League
import ee.example.footballclubs.data.remote.TheSportsDbApi
import ee.example.footballclubs.domain.Constants.SPORT_TYPE_SOCCER

//This Repository class manages league data, offering tools for searching leagues via a remote API and managing database storage for search results.
class FootballRepository(
    private val api: TheSportsDbApi,
    private val database: LeagueDao
) {
    //Searches the local database for leagues matching a given query.

    fun searchLocally(query: String) = database.searchLeagues(query)

    //Searches for leagues using the remote API.

    suspend fun searchOnline(query: String) = api.searchAllLeagues(query, SPORT_TYPE_SOCCER)

    //Saves a list of leagues to the local database.

    suspend fun saveResultToLocalDb(leagues: List<League>){
        database.insertAll(leagues)
    }
}
