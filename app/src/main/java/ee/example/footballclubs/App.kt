package ee.example.footballclubs

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import ee.example.footballclubs.data.local.AppDatabase

@HiltAndroidApp
class App : Application() {
}