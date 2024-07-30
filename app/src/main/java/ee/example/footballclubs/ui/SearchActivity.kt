package ee.example.footballclubs.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ee.example.footballclubs.domain.Constants.KEY_SEARCH_TYPE
import ee.example.footballclubs.domain.Constants.SEARCH_TYPE_CACHED_CLUBS
import ee.example.footballclubs.domain.Constants.SEARCH_TYPE_REMOTE_CLUBS
import ee.example.footballclubs.ui.screen.SearchClubScreen
import ee.example.footballclubs.ui.screen.SearchScreen
import ee.example.footballclubs.ui.ui.theme.FootballClubsTheme
import ee.example.footballclubs.ui.viewModel.SearchViewModel

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    private lateinit var viewModel: SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchType = intent.getStringExtra(KEY_SEARCH_TYPE)
        setContent {
            FootballClubsTheme {
                // A surface container using the 'background' color from the theme
                viewModel = hiltViewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Based on the search type received we will show the UI accordingly
                    when (searchType) {
                        SEARCH_TYPE_REMOTE_CLUBS -> SearchScreen(viewModel = viewModel)
                        SEARCH_TYPE_CACHED_CLUBS -> SearchClubScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
