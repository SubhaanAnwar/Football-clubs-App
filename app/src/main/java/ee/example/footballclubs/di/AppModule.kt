package ee.example.footballclubs.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ee.example.footballclubs.data.FootballRepository
import ee.example.footballclubs.data.local.AppDatabase
import ee.example.footballclubs.data.local.LeagueDao
import ee.example.footballclubs.data.remote.TheSportsDbApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//This module sets application dependencies, constructing essential components like the database, repository layer, and network configuration using Dependency
//Injection design, simplifying code organization and testing.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://www.thesportsdb.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(loggingClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSportsApi(
        retrofit: Retrofit
    ): TheSportsDbApi {
        return retrofit.create(TheSportsDbApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDb(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideLeagueDao(
        db: AppDatabase
    ): LeagueDao {
        return db.leagueDao()
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: TheSportsDbApi,
        dao: LeagueDao
    ): FootballRepository {
        return FootballRepository(api, dao)
    }

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

}