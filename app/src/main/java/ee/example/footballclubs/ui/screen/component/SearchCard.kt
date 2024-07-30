package ee.example.footballclubs.ui.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchCard(
    modifier: Modifier = Modifier,
    buttonText: String,
    onButtonTap: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val (searchText, onTextChange) = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText, onValueChange = onTextChange,
            placeholder = {
                Text(text = "Search for Leagues")
            },
            singleLine = true
        )
        Button(
            onClick = {
                keyboardController?.hide()
                onButtonTap(searchText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SearchCard(buttonText = "Search") {}
}