package ee.example.footballclubs.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ee.example.footballclubs.R
import ee.example.footballclubs.data.model.League

@Composable
fun LeagueItemCardWithImage(modifier: Modifier = Modifier, item: League) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            val imageModifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
            val imagePlaceHolder = painterResource(id = R.drawable.sharp_sports_soccer_24)
            if (item.strLeagueLogo != null) {
                AsyncImage(
                    model = item.strLeagueLogo, contentDescription = null,
                    modifier = imageModifier,
                    placeholder = imagePlaceHolder
                )
            } else {
                Image(
                    painter = imagePlaceHolder,
                    contentDescription = null,
                    modifier = imageModifier
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
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
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LeagueItemCardWithImage(
        item = League(
            "1",
            "English Club",
            "Alternate name", "Soccer",
            "https://placekitten.com/200/200"
        )
    )
}
