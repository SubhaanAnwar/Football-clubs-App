package ee.example.footballclubs.data.remote


import com.google.gson.annotations.SerializedName
import ee.example.footballclubs.data.model.League

//This DTO outlines the structure of football league search responses, including a list of Country objects with league metadata and information.

data class SearchLeaguesDto(
    val countries: List<Country>
) {
    //SearchLeaguesDto is a nested data class that provides information on a specific nation or league, including its unique identity, official name, alternate name, sport type, and logo URL.

    data class Country(
        val dateFirstEvent: String?,
        val idAPIfootball: String?,
        val idCup: String?,
        val idLeague: String?,
        val idSoccerXML: String?,
        val intDivision: String?,
        val intFormedYear: String?,
        val strBadge: String?,
        val strBanner: String?,
        val strComplete: String?,
        val strCountry: String?,
        val strCurrentSeason: String?,
        val strDescriptionCN: String?,
        val strDescriptionDE: String?,
        val strDescriptionEN: String?,
        val strDescriptionES: String?,
        val strDescriptionFR: String?,
        val strDescriptionHU: String?,
        val strDescriptionIL: String?,
        val strDescriptionIT: String?,
        val strDescriptionJP: String?,
        val strDescriptionNL: String?,
        val strDescriptionNO: String?,
        val strDescriptionPL: String?,
        val strDescriptionPT: String?,
        val strDescriptionRU: String?,
        val strDescriptionSE: String?,
        val strFacebook: String?,
        val strFanart1: String?,
        val strFanart2: String?,
        val strFanart3: String?,
        val strFanart4: String?,
        val strGender: String?,
        val strInstagram: String?,
        val strLeague: String?,
        val strLeagueAlternate: String?,
        val strLocked: String?,
        val strLogo: String?,
        val strNaming: String?,
        val strPoster: String?,
        val strRSS: String?,
        val strSport: String?,
        val strTrophy: String?,
        val strTvRights: String?,
        val strTwitter: String?,
        val strWebsite: String?,
        val strYoutube: String?
    ){
        //This extension method simplifies converting a Country object into a League entity by creating a League object using key data from the Country object.

        fun toLeague(): League {
            return League(
                idLeague ?: "",
                strLeague ?: "",
                strLeagueAlternate ?: "",
                strSport ?: "",
                strLogo
            )
        }
    }
}
