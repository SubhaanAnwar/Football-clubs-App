package ee.example.footballclubs.data.local

import android.content.Context
import com.google.gson.Gson
import ee.example.footballclubs.R
import ee.example.footballclubs.data.model.League
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException


class PrepopulateRoom(
    private val context: Context,
    private val dao: LeagueDao,
    private val coroutineScope: CoroutineScope
) {


    fun populateData() {
        val jsonString = readJsonFromRaw(context)
        val leagues = jsonString?.let { parseJson(it) }
        if (leagues != null) {
            coroutineScope.launch { dao.insertAll(leagues) }
        }
    }


    private fun parseJson(jsonString: String): List<League>? {
        // Using a library like Gson to parse the JSON string
        // into a list of League objects
        val gson = Gson()
        val response = gson.fromJson(jsonString, LeagueResponse::class.java)
        return response.leagues
    }

    private fun readJsonFromRaw(context: Context): String? {
        return try {
            val inputStream = context.resources.openRawResource(R.raw.football_leagues)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

//data class to model the top-level structure of the JSON response containing the list of leagues.
private data class LeagueResponse(val leagues: List<League>)
