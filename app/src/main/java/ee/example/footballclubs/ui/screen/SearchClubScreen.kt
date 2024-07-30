package ee.example.footballclubs.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ee.example.footballclubs.data.model.League
import ee.example.footballclubs.ui.screen.component.LeagueItemCardWithImage
import ee.example.footballclubs.ui.screen.component.SearchCard
import ee.example.footballclubs.ui.viewModel.SearchViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchClubScreen(modifier: Modifier = Modifier, viewModel: SearchViewModel) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val items = viewModel.searchResults.collectAsState()
        LazyColumn {
            stickyHeader {
                Surface(color = MaterialTheme.colorScheme.background) {    // You can search the clubs
                    SearchCard(buttonText = "Search") {
                        viewModel.searchClubs(query = it)
                    }
                }
            }
            items(items.value) { item: League ->
                LeagueItemCardWithImage(item = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}