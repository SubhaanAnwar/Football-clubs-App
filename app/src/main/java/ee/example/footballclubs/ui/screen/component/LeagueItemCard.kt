package ee.example.footballclubs.ui.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ee.example.footballclubs.data.model.League

@Composable
fun LeagueItemCard(modifier: Modifier = Modifier, item: League) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = item.strLeague, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "Sport: ${item.strSport}")
            Text(text = "Alternate: ${item.strLeagueAlternate}")
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    LeagueItemCard(item = League("1", "English Club", "Alternate name", "Soccer"))
}