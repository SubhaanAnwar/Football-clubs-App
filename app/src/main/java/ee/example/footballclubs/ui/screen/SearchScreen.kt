package ee.example.footballclubs.ui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ee.example.footballclubs.data.model.League
import ee.example.footballclubs.ui.screen.component.LeagueItemCard
import ee.example.footballclubs.ui.screen.component.SearchCard
import ee.example.footballclubs.ui.viewModel.SearchViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, viewModel: SearchViewModel) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn {
            stickyHeader {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SearchCard(buttonText = "Retrieve Clubs") { //Buttons
                        viewModel.retrieveClubs(query = it)
                    }
                }
            }
            items(viewModel.remoteSearchResult) { item: League ->
                LeagueItemCard(item = item)
                Spacer(modifier = Modifier.height(8.dp))  //Design
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        AnimatedVisibility(
            visible = viewModel.isLoading,
            modifier = Modifier.align(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = viewModel.isLoading.not() && viewModel.remoteSearchResult.isEmpty(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "¯\\_(ツ)_/¯" +
                    "\nThere is no league for the entered query",  //Design for query
                textAlign = TextAlign.Center)
        }

        Button(
            onClick = {
                viewModel.cacheResult()
                Toast.makeText(
                    context,
                    "Saved to db",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)  //Text displayed if button clicked to save the data
        ) {
            Text(text = "Save clubs to Database")
        }
    }
}