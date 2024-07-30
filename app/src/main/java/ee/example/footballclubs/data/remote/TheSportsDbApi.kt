package ee.example.footballclubs.data.remote

import ee.example.footballclubs.domain.Constants.SPORT_TYPE_SOCCER
import retrofit2.http.GET
import retrofit2.http.Query

//The Sports DB API interface, likely at https://www.thesportsdb.com/, outlines methods for interacting with the platform, involving internet queries for sports-related data.
interface TheSportsDbApi {
    //Get request and query for c and s
    @GET("api/v1/json/3/search_all_leagues.php")
    suspend fun searchAllLeagues(
        @Query("c") country: String,
        @Query("s") sport: String
    ): SearchLeaguesDto
}
