package ee.example.footballclubs.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.example.footballclubs.data.FootballRepository
import ee.example.footballclubs.data.model.League
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//This ViewModel manages football league search features, utilizing FootballRepository, _searchEvent property, and SharedFlow to interface with data sources and
// store user-initiated queries.


//The ViewModel uses a Boolean flag, searchClubs, retrieveClubs, and cacheResult methods to ensure network activities are ongoing, store league data, and maintain data continuity.
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel @Inject constructor(
    private val repository: FootballRepository
) : ViewModel() {

    private val _searchEvent = MutableSharedFlow<String>()
    val searchEvent: SharedFlow<String> = _searchEvent

    var isLoading by mutableStateOf(false)
        private set

    private val _searchResults = _searchEvent.flatMapLatest { query ->
        repository.searchLocally(query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000L),
        initialValue = emptyList()
    )
    val searchResults: StateFlow<List<League>> = _searchResults

    private val _remoteSearchResult = mutableStateListOf<League>()
    val remoteSearchResult: List<League> = _remoteSearchResult

    fun searchClubs(query: String) {
        viewModelScope.launch { _searchEvent.emit(query.trim()) }
    }

    fun retrieveClubs(query: String) {
        isLoading = true
        _remoteSearchResult.clear()
        viewModelScope.launch {
            val result = try {
                repository.searchOnline(query.trim()).countries
            } catch (e: Exception) {
                Log.e("API", "retrieveClubs", e)
                emptyList()
            }
            if (result.isNullOrEmpty().not()) {
                result.forEach {
                    _remoteSearchResult.add(it.toLeague())
                }
            }
            isLoading = false
        }
    }

    fun cacheResult() {
        if (remoteSearchResult.isNotEmpty()) {
            viewModelScope.launch { repository.saveResultToLocalDb(remoteSearchResult) }
        }
    }
}
