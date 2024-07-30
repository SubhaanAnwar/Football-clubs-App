package ee.example.footballclubs.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ee.example.footballclubs.domain.Constants.KEY_SEARCH_TYPE
import ee.example.footballclubs.domain.Constants.SEARCH_TYPE_CACHED_CLUBS
import ee.example.footballclubs.domain.Constants.SEARCH_TYPE_REMOTE_CLUBS
import ee.example.footballclubs.ui.SearchActivity

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onPopulateClickButton: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onPopulateClickButton) {    //All the buttons for the home screen
            Text(text = "Add Leagues to DB")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            startSearchActivity(context, SEARCH_TYPE_REMOTE_CLUBS)
        }) {
            Text(text = "Search for Clubs By League ")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            startSearchActivity(context, SEARCH_TYPE_CACHED_CLUBS)
        }) {
            Text(text = "Search for Clubs")
        }
    }
}

//The button will initiate search activity and send intent data to display the appropriate user interface.

private fun startSearchActivity(context: Context, searchType: String) {
    with(context) {
        val intent = Intent(this, SearchActivity::class.java).apply {
            putExtra(KEY_SEARCH_TYPE, searchType)
        }
        startActivity(intent)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen() {}
}