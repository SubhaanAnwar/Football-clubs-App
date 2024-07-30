//
package ee.example.footballclubs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ee.example.footballclubs.data.local.LeagueDao
import ee.example.footballclubs.data.local.PrepopulateRoom
import ee.example.footballclubs.ui.screen.HomeScreen
import ee.example.footballclubs.ui.theme.FootballClubsTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var dao: LeagueDao

    private lateinit var populateDb: PrepopulateRoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateDb = PrepopulateRoom(this, dao, lifecycleScope)
        setContent {
            FootballClubsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen() {
                        //when user click on add leagues to db button this part will be triggered
                        populateDb.populateData()
                        Toast.makeText(this, "Data added in local db", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


//LINKS
//https://www.youtube.com/watch?v=lwAvI3WDXBY&list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o
//https://www.youtube.com/watch?v=OxHNcCXnxnE
//https://www.youtube.com/watch?v=hJPk50p7xwA&list=PLSrm9z4zp4mGK0g_0_jxYGgg3os9tqRUQ
//https://www.youtube.com/watch?v=bOd3wO0uFr8
//https://developer.android.com/training/data-storage/sqlite
//https://www.geeksforgeeks.org/android-sqlite-database-in-kotlin/
//https://stackoverflow.com/questions/74832039/how-to-attach-filled-sqlite-db-to-app-with-kotlin-and-android-studio
//https://developer.android.com/develop/ui/compose/kotlin#:~:text=Stay%20organized%20with%20collections%20Save%20and%20categorize%20content%20based%20on%20your%20preferences.&text=Jetpack%20Compose%20is%20built%20around,to%20write%20good%20Compose%20code.
//https://www.youtube.com/watch?v=6_wK_Ud8--0
//https://www.youtube.com/watch?v=x8TYLiigOXM&t=2s
//https://www.youtube.com/watch?v=dEEyZkZekvI